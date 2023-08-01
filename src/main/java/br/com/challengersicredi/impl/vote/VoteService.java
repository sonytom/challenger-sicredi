package br.com.challengersicredi.impl.vote;

import br.com.challengersicredi.commons.enums.ScheduleStatusEnum;
import br.com.challengersicredi.commons.enums.VoteOptionsType;
import br.com.challengersicredi.commons.exeption.ResourceNotFound;
import br.com.challengersicredi.commons.exeption.SessionClosed;
import br.com.challengersicredi.commons.exeption.SessionNotOpenOrClosed;
import br.com.challengersicredi.commons.exeption.VoteDuplicated;
import br.com.challengersicredi.impl.schedule.entity.ScheduleModelEntity;
import br.com.challengersicredi.impl.schedule.repository.ScheduleRepository;
import br.com.challengersicredi.impl.vote.entity.VoteModelEntity;
import br.com.challengersicredi.impl.vote.model.request.VoteModelImpl;
import br.com.challengersicredi.impl.vote.model.response.BuildCountModel;
import br.com.challengersicredi.impl.vote.model.response.VoteModelImplResponse;
import br.com.challengersicredi.impl.vote.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final ScheduleRepository scheduleRepository;

    public Mono<VoteModelImplResponse> voteScheduleSession(VoteModelImpl voteModel) {
        return scheduleRepository.findByName(voteModel.getScheduleName())
                .filter(schedule -> schedule.getStatus().equals(ScheduleStatusEnum.OPEN))
                .switchIfEmpty(Mono.error(new SessionNotOpenOrClosed()))
                .flatMap(schedule -> {
                    if (LocalDateTime.now().isAfter(schedule.getExpiration())) {
                        schedule.setStatus(ScheduleStatusEnum.CLOSED);
                        return scheduleRepository.save(schedule)
                                .then(Mono.error(new SessionClosed()));
                    }
                    return saveVoteAndCreateResponse(schedule, voteModel)
                            .onErrorResume(throwable -> Mono.error(new VoteDuplicated()));
                });
    }

    private Mono<VoteModelImplResponse> saveVoteAndCreateResponse(ScheduleModelEntity schedule, VoteModelImpl voteModel) {
        VoteModelEntity voteEntity = VoteModelEntity.builder()
                .userId(voteModel.getUserId())
                .name(schedule.getName())
                .votedDate(LocalDateTime.now())
                .voteOptionType(voteModel.getVoteOptionType())
                .scheduleName(voteModel.getScheduleName())
                .build();

        return voteRepository.save(voteEntity)
                .thenReturn(VoteModelImplResponse.builder()
                        .userId(voteModel.getUserId())
                        .name(schedule.getName())
                        .votedDate(LocalDateTime.now())
                        .voteOptionType(voteModel.getVoteOptionType())
                        .scheduleName(voteModel.getScheduleName())
                        .build());
    }

    public Mono<BuildCountModel> countVotes(String scheduleName, Integer session) {
        return scheduleRepository.findByName(scheduleName)
                .switchIfEmpty(Mono.error(new ResourceNotFound()))
                .flatMap(schedule -> {
                    var yesVotesMono = voteRepository.countByScheduleNameAndVoteOptionType(scheduleName, VoteOptionsType.YES);
                    var noVotesMono = voteRepository.countByScheduleNameAndVoteOptionType(scheduleName, VoteOptionsType.NO);

                    return Mono.zip(yesVotesMono, noVotesMono)
                            .flatMap(tuple -> {
                                Long yesVote = tuple.getT1();
                                Long noVote = tuple.getT2();

                                int totalVotes = Math.toIntExact(yesVote + noVote);
                                var builder = BuildCountModel.builder()
                                        .scheduleName(schedule.getName())
                                        .scheduleStatus(schedule.getStatus().toString())
                                        .yes(String.valueOf(yesVote))
                                        .totalVotes(totalVotes)
                                        .no(String.valueOf(noVote));

                                if (LocalDateTime.now().isAfter(schedule.getExpiration()) && session == 1) {
                                    schedule.setStatus(ScheduleStatusEnum.CLOSED);
                                    schedule.setTotalVotes(totalVotes);
                                    return scheduleRepository.save(schedule).thenReturn(builder.build());
                                }
                                return Mono.just(builder.build());
                            });
                });
    }

}

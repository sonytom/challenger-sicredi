package br.com.challengersicredi.impl.vote;

import br.com.challengersicredi.commons.schedule.enums.ScheduleStatusEnum;
import br.com.challengersicredi.commons.schedule.enums.VoteOptionsType;
import br.com.challengersicredi.commons.schedule.exeption.ResourceNotFound;
import br.com.challengersicredi.commons.schedule.exeption.SessionClosed;
import br.com.challengersicredi.impl.schedule.repository.ScheduleRepository;
import br.com.challengersicredi.impl.vote.entity.VoteModelEntity;
import br.com.challengersicredi.impl.vote.mapper.VoteModelImplMapper;
import br.com.challengersicredi.impl.vote.model.request.VoteModelImpl;
import br.com.challengersicredi.impl.vote.model.response.BuildCountModel;
import br.com.challengersicredi.impl.vote.model.response.VoteModelImplResponse;
import br.com.challengersicredi.impl.vote.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final ScheduleRepository scheduleRepository;

    public Mono<VoteModelImplResponse> voteScheduleSession(VoteModelImpl voteModel) {

        return scheduleRepository.findByName(voteModel.getScheduleName())
                .filter(schedule -> schedule.getStatus().equals(ScheduleStatusEnum.OPEN))
                .publishOn(Schedulers.boundedElastic())
                .map(schedule -> {
                    if (LocalDateTime.now().isAfter(schedule.getExpiration())) {
                        schedule.setStatus(ScheduleStatusEnum.CLOSED);
                        scheduleRepository.save(schedule).subscribe();
                        throw new SessionClosed();
                    }
                    return VoteModelImplResponse.builder()
                            .userId(voteModel.getUserId())
                            .name(schedule.getName())
                            .votedDate(LocalDateTime.now())
                            .voteOptionType(voteModel.getVoteOptionType())
                            .scheduleName(voteModel.getScheduleName())
                            .build();
                }).doOnNext(votes -> voteRepository.save(VoteModelEntity.builder()
                                .userId(votes.getUserId())
                                .name(votes.getName())
                                .votedDate(LocalDateTime.now())
                                .voteOptionType(voteModel.getVoteOptionType().toString())
                                .scheduleName(voteModel.getScheduleName())
                                .build())
                        .map(VoteModelImplMapper::mapToResponse).subscribe());
    }

    public Mono<BuildCountModel> countVotes(String scheduleName) {
        return scheduleRepository.findByName(scheduleName)
                .filter(Objects::nonNull)
                .flatMap(schedule ->
                        voteRepository.countByScheduleNameAndVoteOptionType(scheduleName, VoteOptionsType.YES)
                                .map(yesVote -> BuildCountModel.builder()
                                        .scheduleName(schedule.getName())
                                        .ScheduleStatus(schedule.getStatus().toString())
                                        .yes(yesVote.toString())
                                        .build()).switchIfEmpty(Mono.error(new ResourceNotFound()))
                ).flatMap(it -> voteRepository.countByScheduleNameAndVoteOptionType(scheduleName, VoteOptionsType.NO)
                        .map(noVote -> BuildCountModel.builder()
                                .scheduleName(it.getScheduleName())
                                .ScheduleStatus(it.getScheduleStatus())
                                .yes(it.getYes())
                                .no(noVote.toString())
                                .build())
                        .switchIfEmpty(Mono.error(new ResourceNotFound())));
    }
}

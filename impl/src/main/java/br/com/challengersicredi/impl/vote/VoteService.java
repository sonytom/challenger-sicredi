package br.com.challengersicredi.impl.vote;

import br.com.challengersicredi.commons.schedule.enums.ScheduleStatusEnum;
import br.com.challengersicredi.commons.schedule.enums.VoteOptionsType;
import br.com.challengersicredi.impl.schedule.entity.ScheduleModelEntity;
import br.com.challengersicredi.impl.schedule.repository.ScheduleRepository;
import br.com.challengersicredi.impl.vote.entity.VoteModelEntity;
import br.com.challengersicredi.impl.vote.mapper.VoteModelImplMapper;
import br.com.challengersicredi.impl.vote.model.request.VoteModelImpl;
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
    private VoteRepository voteRepository;
    private ScheduleRepository scheduleRepository;

    public Mono<VoteModelImplResponse> voteScheduleSession(VoteModelImpl voteModel) {

        return scheduleRepository
                .findByName(voteModel.getScheduleName())
                .filter(schedule -> schedule.getStatus().equals(ScheduleStatusEnum.OPEN))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(schedule -> {

                    if (LocalDateTime.now().isAfter(schedule.getExpiration())) {
                        schedule.setStatus(ScheduleStatusEnum.CLOSED);

                        scheduleRepository.save(schedule).subscribe();
                        throw new RuntimeException();
                    }

                    voteRepository.findByUserIdAndScheduleName(
                            schedule.getId(), schedule.getName()
                    ).map(votes -> {
                        if (votes != null) throw new RuntimeException();

                        schedule.setTotalVotes(schedule.getTotalVotes() + 1);

                       return VoteModelImpl.builder()
                                .userId(voteModel.getUserId())
                                .name(voteModel.getName())
                                .votedDate(LocalDateTime.now())
                                .voteOptionType(voteModel.getVoteOptionType())
                                .scheduleName(voteModel.getScheduleName())
                                .build();
                    } ).subscribe();
                }).map(it -> voteRepository.save(VoteModelEntity.builder()
                        .build()))
                .flatMap(it -> it.map(VoteModelImplMapper::mapToResponse))
                .switchIfEmpty(Mono.empty());
    }

    public Mono<VoteModelImplResponse> countVotes(String scheduleName, VoteOptionsType optionsType) {
    return scheduleRepository.findByName(scheduleName).filter(Objects::nonNull)
            .flatMap(a -> voteRepository.countByScheduleNameAndVoteOptionType(scheduleName,optionsType)
                    .map(newI -> VoteModelImplResponse.builder()
                            .id(newI.getId())
                            .build()));
    }
}

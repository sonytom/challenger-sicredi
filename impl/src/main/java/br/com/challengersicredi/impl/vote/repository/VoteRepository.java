package br.com.challengersicredi.impl.vote.repository;

import br.com.challengersicredi.commons.schedule.enums.VoteOptionsType;
import br.com.challengersicredi.impl.vote.entity.VoteModelEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VoteRepository extends ReactiveMongoRepository<VoteModelEntity, String> {
    Mono<VoteModelEntity> findByUserIdAndScheduleName(String userID,String name);
    Mono<VoteModelEntity> countByScheduleNameAndVoteOptionType(String schedulerName, VoteOptionsType voteOptionsType);
}
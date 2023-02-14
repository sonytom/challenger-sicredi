package br.com.challengersicredi.impl.schedule.repository;

import br.com.challengersicredi.impl.schedule.entity.ScheduleModelEntity;
import br.com.challengersicredi.impl.vote.entity.VoteModelEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ScheduleRepository extends ReactiveMongoRepository<ScheduleModelEntity, String> {
    Mono<ScheduleModelEntity> findByName(String name);
    Mono<ScheduleModelEntity> findByIdAndName(String userID, String name);

}
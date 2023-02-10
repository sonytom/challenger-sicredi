package br.com.challengersicredi.impl.schedule.repository;

import br.com.challengersicredi.impl.schedule.entity.ScheduleModelEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends ReactiveMongoRepository<ScheduleModelEntity, String> {
}
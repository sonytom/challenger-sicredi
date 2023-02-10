package br.com.challengersicredi.impl.schedule;

import br.com.challengersicredi.impl.schedule.mapper.ScheduleModelImplMapper;
import br.com.challengersicredi.impl.schedule.model.request.ScheduleModelImplRequest;
import br.com.challengersicredi.impl.schedule.model.response.ScheduleModelImplResponse;
import br.com.challengersicredi.impl.schedule.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.challengersicredi.impl.schedule.mapper.ScheduleModelImplMapper.mapTo;

@Service
@AllArgsConstructor
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    public Mono<ScheduleModelImplResponse> save(ScheduleModelImplRequest scheduleModelImplRequest) {


        return scheduleRepository.save(mapTo(scheduleModelImplRequest))
                .map(ScheduleModelImplMapper::mapToResponse)
                .switchIfEmpty(Mono.empty());
    }

}

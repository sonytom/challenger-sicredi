package br.com.challengersicredi.impl.schedule;

import br.com.challengersicredi.commons.schedule.enums.ScheduleStatusEnum;
import br.com.challengersicredi.impl.schedule.mapper.ScheduleModelImplMapper;
import br.com.challengersicredi.impl.schedule.model.request.ScheduleModelImpl;
import br.com.challengersicredi.impl.schedule.model.response.ScheduleModelImplResponse;
import br.com.challengersicredi.impl.schedule.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static br.com.challengersicredi.impl.schedule.mapper.ScheduleModelImplMapper.mapTo;

@Service
@AllArgsConstructor
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    private static final Integer DEFAULT_EXPIRATION_TIME = 1;

    public Mono<ScheduleModelImplResponse> save(ScheduleModelImpl scheduleModelImpl) {
        return scheduleRepository.save(mapTo(scheduleModelImpl))
                .map(ScheduleModelImplMapper::mapToResponse)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ScheduleModelImplResponse> openSession(String scheduleName) {

        return scheduleRepository.findByName(scheduleName)
                .filter(it -> it.getStatus().equals(ScheduleStatusEnum.CREATED))
                .map(it -> {                                         // colocar na request body
                    it.setExpiration(LocalDateTime.now().plusMinutes(DEFAULT_EXPIRATION_TIME));
                    it.setOpenAt(LocalDateTime.now());
                    it.setStatus(ScheduleStatusEnum.OPEN);
                    it.setTotalVotes(0);
                    return it;
                })
                .map(it -> scheduleRepository.save(it))
                .flatMap(scheduleSession -> scheduleSession.map(ScheduleModelImplMapper::mapToResponse))
                .switchIfEmpty(Mono.empty());
    }
}

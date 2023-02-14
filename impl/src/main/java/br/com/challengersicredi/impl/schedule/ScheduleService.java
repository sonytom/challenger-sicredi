package br.com.challengersicredi.impl.schedule;

import br.com.challengersicredi.commons.schedule.enums.ScheduleStatusEnum;
import br.com.challengersicredi.impl.schedule.mapper.ScheduleModelImplMapper;
import br.com.challengersicredi.impl.schedule.model.request.ScheduleModelImpl;
import br.com.challengersicredi.impl.schedule.model.response.ScheduleImplResponse;
import br.com.challengersicredi.impl.schedule.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static br.com.challengersicredi.impl.schedule.mapper.ScheduleModelImplMapper.mapTo;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    private static final Integer DEFAULT_EXPIRATION_TIME = 1;

    public Mono<ScheduleImplResponse> save(ScheduleModelImpl scheduleModelImpl) {
        return scheduleRepository.save(mapTo(scheduleModelImpl))
                .map(ScheduleModelImplMapper::mapToResponse)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ScheduleImplResponse> openSession(String scheduleName, Integer minutesSession) {
        return scheduleRepository.findByName(scheduleName)
                .filter(schedule -> schedule.getStatus().equals(ScheduleStatusEnum.CREATED))
                .map(it -> {
                    it.setExpiration(LocalDateTime.now().plusMinutes(getMinutes(minutesSession)));
                    it.setOpenAt(LocalDateTime.now());
                    it.setStatus(ScheduleStatusEnum.OPEN);
                    it.setTotalVotes(0);
                    return it;
                })
                .map(scheduleRepository::save)
                .flatMap(scheduleSession -> scheduleSession.map(ScheduleModelImplMapper::mapToResponse))
                .switchIfEmpty(Mono.empty());
    }

    private static Integer getMinutes(Integer minutesSession) {
        if (minutesSession == null || minutesSession <= 0 || minutesSession > 60)
            return DEFAULT_EXPIRATION_TIME;
        return minutesSession;
    }
}

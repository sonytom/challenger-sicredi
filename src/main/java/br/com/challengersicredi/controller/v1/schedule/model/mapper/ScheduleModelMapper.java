package br.com.challengersicredi.controller.v1.schedule.model.mapper;

import br.com.challengersicredi.controller.v1.schedule.model.request.ScheduleModelRequest;
import br.com.challengersicredi.impl.schedule.model.request.ScheduleModelImpl;
import org.springframework.stereotype.Component;

@Component
public class ScheduleModelMapper {
    public static ScheduleModelImpl mapFrom(ScheduleModelRequest scheduleModelRequest) {
        return ScheduleModelImpl.builder()
                .name(scheduleModelRequest.getName())
                .build();
    }
}

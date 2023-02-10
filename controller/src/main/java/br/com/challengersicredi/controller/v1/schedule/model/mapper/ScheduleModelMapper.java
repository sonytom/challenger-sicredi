package br.com.challengersicredi.controller.v1.schedule.model.mapper;

import br.com.challengersicredi.controller.v1.schedule.model.request.ScheduleModelRequest;
import br.com.challengersicredi.impl.schedule.model.request.ScheduleModelImplRequest;
import org.springframework.stereotype.Component;

@Component
public class ScheduleModelMapper {
    public static ScheduleModelImplRequest mapFrom(ScheduleModelRequest scheduleModelRequest) {
        return ScheduleModelImplRequest.builder()
                .name(scheduleModelRequest.getName())
                .build();
    }
}

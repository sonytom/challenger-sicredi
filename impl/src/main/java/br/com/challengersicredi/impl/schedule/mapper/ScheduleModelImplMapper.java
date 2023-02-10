package br.com.challengersicredi.impl.schedule.mapper;

import br.com.challengersicredi.commons.schedule.enums.ScheduleStatusEnum;
import br.com.challengersicredi.impl.schedule.entity.ScheduleModelEntity;
import br.com.challengersicredi.impl.schedule.model.request.ScheduleModelImplRequest;
import br.com.challengersicredi.impl.schedule.model.response.ScheduleModelImplResponse;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class ScheduleModelImplMapper {


    public static ScheduleModelImplResponse mapToResponse(ScheduleModelEntity schedule) {
        return ScheduleModelImplResponse.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .createAt(schedule.getCreateAt())
                .OpenAt(schedule.getOpenAt())
                .expiration(schedule.getExpiration())
                .status(schedule.getStatus())
                .totalVotes(schedule.getTotalVotes())
                .build();
    }

    public static ScheduleModelEntity mapTo(ScheduleModelImplRequest scheduleModelImplRequest) {
        return ScheduleModelEntity.builder()
                .name(scheduleModelImplRequest.getName())
                .createAt(LocalTime.now())
                .OpenAt(LocalTime.now())
                .expiration(LocalTime.now().plusMinutes(1))
                .status(ScheduleStatusEnum.OPEN)
                .totalVotes(2)
                .build();
    }
}

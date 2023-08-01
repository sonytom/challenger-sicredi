package br.com.challengersicredi.impl.schedule.mapper;

import br.com.challengersicredi.commons.enums.ScheduleStatusEnum;
import br.com.challengersicredi.impl.schedule.entity.ScheduleModelEntity;
import br.com.challengersicredi.impl.schedule.model.request.ScheduleModelImpl;
import br.com.challengersicredi.impl.schedule.model.response.ScheduleImplResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduleModelImplMapper {
    public static ScheduleImplResponse mapToResponse(ScheduleModelEntity schedule) {
        return ScheduleImplResponse.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .createAt(schedule.getCreateAt())
                .OpenAt(schedule.getOpenAt())
                .expiration(schedule.getExpiration())
                .status(schedule.getStatus())
                .totalVotes(schedule.getTotalVotes())
                .build();
    }

    public static ScheduleModelEntity mapTo(ScheduleModelImpl scheduleModelImpl) {
        return ScheduleModelEntity.builder()
                .name(scheduleModelImpl.getName())
                .createAt(LocalDateTime.now())
                .OpenAt(LocalDateTime.now())
                .status(ScheduleStatusEnum.CREATED)
                .build();
    }
}

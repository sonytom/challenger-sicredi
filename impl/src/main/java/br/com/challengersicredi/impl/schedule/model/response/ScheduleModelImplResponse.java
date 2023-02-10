package br.com.challengersicredi.impl.schedule.model.response;

import br.com.challengersicredi.commons.schedule.enums.ScheduleStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class ScheduleModelImplResponse {
    private String id;
    private String name;
    private LocalTime createAt;
    private LocalTime OpenAt;
    private LocalTime expiration;
    private ScheduleStatusEnum status;
    private Integer totalVotes;
}

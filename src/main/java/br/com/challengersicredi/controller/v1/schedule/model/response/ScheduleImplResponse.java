package br.com.challengersicredi.controller.v1.schedule.model.response;

import br.com.challengersicredi.commons.enums.ScheduleStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScheduleImplResponse {
    private String id;
    private String name;
    private LocalTime createAt;
    private LocalTime OpenAt;
    private LocalTime expiration;
    private ScheduleStatusEnum status;
    private Integer totalVotes;
}

package br.com.challengersicredi.impl.schedule.entity;

import br.com.challengersicredi.commons.schedule.enums.ScheduleStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "Schedule")
public class ScheduleModelEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private LocalTime createAt;
    private LocalTime OpenAt;
    private LocalTime expiration;
    private ScheduleStatusEnum status;
    private Integer totalVotes;
}
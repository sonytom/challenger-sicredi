package br.com.challengersicredi.impl.vote.entity;

import br.com.challengersicredi.commons.schedule.enums.ScheduleStatusEnum;
import br.com.challengersicredi.commons.schedule.enums.VoteOptionsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "vote")
@CompoundIndex(def = "{'userId':1, 'scheduleName':1}", unique = true)
public class VoteModelEntity {
    @Id
    private String id;
    private String userId;
    private String name;
    private LocalDateTime votedDate;
    private VoteOptionsType voteOptionType;
    private String scheduleName;
}


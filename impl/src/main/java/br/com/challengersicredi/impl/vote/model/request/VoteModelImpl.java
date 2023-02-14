package br.com.challengersicredi.impl.vote.model.request;

import br.com.challengersicredi.commons.schedule.enums.VoteOptionsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class VoteModelImpl {
    private String id;
    private String userId;
    private String name;
    private LocalDateTime votedDate;
    private VoteOptionsType voteOptionType;
    private String scheduleName;
}
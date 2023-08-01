package br.com.challengersicredi.impl.vote.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class BuildCountModel {
    private String scheduleName;
    private String scheduleStatus;
    private String yes;
    private String no;
    private Integer totalVotes;

}

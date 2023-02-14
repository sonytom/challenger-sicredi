package br.com.challengersicredi.controller.v1.vote.model.response;

import br.com.challengersicredi.commons.schedule.enums.VoteOptionsType;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;

public class VoteModelResponse {
    @Id
    private String id;
    private String userId;
    private String name;
    private LocalTime votedDate;
    private VoteOptionsType voteOptionType;
    private String scheduleName;
}

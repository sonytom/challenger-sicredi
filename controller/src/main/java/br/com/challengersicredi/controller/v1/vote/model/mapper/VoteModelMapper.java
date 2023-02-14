package br.com.challengersicredi.controller.v1.vote.model.mapper;

import br.com.challengersicredi.controller.v1.vote.model.request.VoteModelRequest;
import br.com.challengersicredi.impl.vote.model.request.VoteModelImpl;
import org.springframework.stereotype.Component;

import static br.com.challengersicredi.commons.schedule.enums.VoteOptionsType.fromValue;

@Component
public class VoteModelMapper {

    public static VoteModelImpl mapForm(VoteModelRequest voteModelRequest) throws Exception {
        return VoteModelImpl.builder()
                .userId(voteModelRequest.getUserId())
                .scheduleName(voteModelRequest.getScheduleName())
                .voteOptionType(fromValue(voteModelRequest.getVoteType()))
                .build();
    }
}
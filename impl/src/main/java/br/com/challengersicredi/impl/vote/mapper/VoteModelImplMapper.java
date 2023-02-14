package br.com.challengersicredi.impl.vote.mapper;

import br.com.challengersicredi.impl.vote.entity.VoteModelEntity;
import br.com.challengersicredi.impl.vote.model.response.VoteModelImplResponse;
import org.springframework.stereotype.Component;

@Component
public class VoteModelImplMapper {
    public static VoteModelImplResponse mapToResponse(VoteModelEntity voteModelEntity) {
        return VoteModelImplResponse.builder()
                .id(voteModelEntity.getId())
                .userId(voteModelEntity.getUserId())
                .name(voteModelEntity.getName())
                .votedDate(voteModelEntity.getVotedDate())
                .scheduleName(voteModelEntity.getScheduleName())
                .build();
    }
}


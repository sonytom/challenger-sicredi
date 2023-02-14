package br.com.challengersicredi.controller.v1.vote.model.request;

import jakarta.validation.constraints.NotBlank;
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
public class VoteModelRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String scheduleName;
    @NotBlank
    private String voteType;
}
package br.com.challengersicredi.controller.v1.vote;

import br.com.challengersicredi.commons.enums.VoteOptionsType;
import br.com.challengersicredi.controller.v1.vote.model.mapper.VoteModelMapper;
import br.com.challengersicredi.controller.v1.vote.model.request.VoteModelRequest;
import br.com.challengersicredi.impl.vote.VoteService;
import br.com.challengersicredi.impl.vote.model.response.BuildCountModel;
import br.com.challengersicredi.impl.vote.model.response.VoteModelImplResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/votes")
public class VoteController {

    private VoteService voteService;
    @PostMapping
    public Mono<VoteModelImplResponse> voteSchedule(@RequestBody @Valid VoteModelRequest voteModelRequest) throws Exception {
        return voteService.voteScheduleSession(VoteModelMapper.mapForm(voteModelRequest));
    }

    @GetMapping("/{scheduleName}/{closeSession}")
    public Mono<BuildCountModel> countVotes (@PathVariable(value = "scheduleName") String scheduleName,
                                             @PathVariable(value = "closeSession",required = false) Integer closeSession) {
        return voteService.countVotes(scheduleName,closeSession);
    }



}

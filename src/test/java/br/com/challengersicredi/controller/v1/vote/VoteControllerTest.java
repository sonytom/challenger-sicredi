package br.com.challengersicredi.controller.v1.vote;

import br.com.challengersicredi.controller.v1.vote.model.request.VoteModelRequest;
import br.com.challengersicredi.impl.vote.VoteService;
import br.com.challengersicredi.impl.vote.model.response.BuildCountModel;
import br.com.challengersicredi.impl.vote.model.response.VoteModelImplResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

public class VoteControllerTest {

    @Mock
    private VoteService voteService;

    @InjectMocks
    private VoteController voteController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(voteController).build();
    }

    @Test
    void testVoteSchedule_SuccessfulVote() {
        VoteModelRequest voteModelRequest = new VoteModelRequest();
        voteModelRequest.setUserId("user123");
        voteModelRequest.setScheduleName("Test Schedule");
        voteModelRequest.setVoteType("Sim");

        var expectedResponse = new VoteModelImplResponse();

        when(voteService.voteScheduleSession(any())).thenReturn(Mono.just(expectedResponse));

        webTestClient.post().uri("/v1/votes")
                .bodyValue(voteModelRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(VoteModelImplResponse.class)
                .value(response -> {
                });

        verify(voteService).voteScheduleSession(any());
    }

    @Test
    void testCountVotes_SuccessfulCount() {
        var scheduleName = "Test Schedule";
        var closeSession = 1;

        var expectedResponse = new BuildCountModel();

        when(voteService.countVotes(eq(scheduleName), eq(closeSession))).thenReturn(Mono.just(expectedResponse));

        webTestClient.get().uri("/v1/votes/{scheduleName}/{closeSession}", scheduleName, closeSession)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BuildCountModel.class)
                .value(response -> {
               });

        verify(voteService).countVotes(eq(scheduleName), eq(closeSession));
    }

}




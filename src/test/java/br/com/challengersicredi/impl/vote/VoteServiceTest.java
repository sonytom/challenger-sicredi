package br.com.challengersicredi.impl.vote;

import br.com.challengersicredi.commons.enums.ScheduleStatusEnum;
import br.com.challengersicredi.commons.enums.VoteOptionsType;
import br.com.challengersicredi.impl.schedule.entity.ScheduleModelEntity;
import br.com.challengersicredi.impl.schedule.repository.ScheduleRepository;
import br.com.challengersicredi.impl.vote.entity.VoteModelEntity;
import br.com.challengersicredi.impl.vote.model.request.VoteModelImpl;
import br.com.challengersicredi.impl.vote.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class VoteServiceTest {

    private VoteRepository voteRepository;
    private ScheduleRepository scheduleRepository;
    private VoteService voteService;

    @BeforeEach
    void setUp() {
        voteRepository = mock(VoteRepository.class);
        scheduleRepository = mock(ScheduleRepository.class);
        voteService = new VoteService(voteRepository, scheduleRepository);
    }

    @Test
    void testVoteScheduleSession_SuccessfulVote() {
        var scheduleName = "Test Schedule";
        var userId = "user123";
        var voteOptionType = VoteOptionsType.YES;

        var schedule = ScheduleModelEntity.builder()
                .name(scheduleName)
                .status(ScheduleStatusEnum.OPEN)
                .expiration(LocalDateTime.now().plusMinutes(30))
                .build();

        var voteModel = VoteModelImpl.builder()
                .userId(userId)
                .scheduleName(scheduleName)
                .voteOptionType(voteOptionType)
                .build();

        var voteEntity = VoteModelEntity.builder()
                .userId(userId)
                .name(scheduleName)
                .votedDate(LocalDateTime.now())
                .voteOptionType(voteOptionType)
                .scheduleName(scheduleName)
                .build();

        when(scheduleRepository.findByName(eq(scheduleName))).thenReturn(Mono.just(schedule));
        when(voteRepository.save(any(VoteModelEntity.class))).thenReturn(Mono.just(voteEntity));

        StepVerifier.create(voteService.voteScheduleSession(voteModel))
                .expectNextMatches(response -> response.getUserId().equals(userId)
                        && response.getScheduleName().equals(scheduleName)
                        && response.getVoteOptionType() == voteOptionType)
                .verifyComplete();

        verify(scheduleRepository).findByName(eq(scheduleName));
        verify(voteRepository).save(any(VoteModelEntity.class));
    }

    @Test
    void testCountVotes_SuccessfulCount() {

        String scheduleName = "Test Schedule";
        var session = 1;
        long yesVotes = 10;
        long noVotes = 5;
        var expiration = LocalDateTime.now().plusMinutes(30);

        ScheduleModelEntity schedule = ScheduleModelEntity.builder()
                .name(scheduleName)
                .status(ScheduleStatusEnum.OPEN)
                .expiration(expiration)
                .build();

        // Mocking repository methods
        when(scheduleRepository.findByName(eq(scheduleName))).thenReturn(Mono.just(schedule));
        when(voteRepository.countByScheduleNameAndVoteOptionType(eq(scheduleName), eq(VoteOptionsType.YES))).thenReturn(Mono.just(yesVotes));
        when(voteRepository.countByScheduleNameAndVoteOptionType(eq(scheduleName), eq(VoteOptionsType.NO))).thenReturn(Mono.just(noVotes));
        when(scheduleRepository.save(any(ScheduleModelEntity.class)))
                .thenReturn(Mono.just(new ScheduleModelEntity()));


        StepVerifier.create(voteService.countVotes(scheduleName, session))
                .expectNextMatches(buildCountModel -> buildCountModel.getScheduleName().equals(scheduleName)
                        && buildCountModel.getScheduleStatus().equals(schedule.getStatus().toString())
                        && buildCountModel.getYes().equals(String.valueOf(yesVotes))
                        && buildCountModel.getNo().equals(String.valueOf(noVotes))
                        && buildCountModel.getTotalVotes() == Math.toIntExact(yesVotes + noVotes))
                .verifyComplete();

        verify(scheduleRepository).findByName(eq(scheduleName));
        verify(voteRepository).countByScheduleNameAndVoteOptionType(eq(scheduleName), eq(VoteOptionsType.YES));
        verify(voteRepository).countByScheduleNameAndVoteOptionType(eq(scheduleName), eq(VoteOptionsType.NO));
    }
}
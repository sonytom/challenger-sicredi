package br.com.challengersicredi.controller.v1.schedule;

import br.com.challengersicredi.controller.v1.schedule.model.mapper.ScheduleModelMapper;
import br.com.challengersicredi.controller.v1.schedule.model.request.ScheduleModelRequest;
import br.com.challengersicredi.impl.schedule.ScheduleService;
import br.com.challengersicredi.impl.schedule.model.response.ScheduleImplResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/schedules")
public class ScheduleController {

    private final ScheduleService service;

    @PostMapping("/save")
    public Mono<ScheduleImplResponse> saveSchedule(@RequestBody @Valid ScheduleModelRequest scheduleModelRequest) {
        return service.save(ScheduleModelMapper.mapFrom(scheduleModelRequest));
    }

    @PatchMapping("/{scheduleName}/{minutesSession}")
    public Mono<ScheduleImplResponse> openSessionRequest(@PathVariable(value = "scheduleName") String scheduleName,
                                                         @PathVariable(value = "minutesSession", required = false) Integer minutes) {
        return service.openSession(scheduleName, minutes);
    }
}
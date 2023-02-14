package br.com.challengersicredi.controller.v1.schedule;

import br.com.challengersicredi.controller.v1.schedule.model.mapper.ScheduleModelMapper;
import br.com.challengersicredi.controller.v1.schedule.model.request.ScheduleModelRequest;
import br.com.challengersicredi.impl.schedule.ScheduleService;
import br.com.challengersicredi.impl.schedule.model.response.ScheduleModelImplResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/schedules")
public class ScheduleController {

    ScheduleService service;

    @PostMapping("/save")
    public Mono<ScheduleModelImplResponse> greeting(@RequestBody @Valid ScheduleModelRequest scheduleModelRequest) {
        return service.save(ScheduleModelMapper.mapFrom(scheduleModelRequest));
    }

    @PatchMapping("/{scheduleName}")
    public Mono<ScheduleModelImplResponse> openSessionRequest(@PathVariable(value = "scheduleName") String scheduleName) {
        return service.openSession(scheduleName);
    }

}
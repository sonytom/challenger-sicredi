package br.com.challengersicredi.controller.v1.schedule;

import br.com.challengersicredi.controller.v1.schedule.model.mapper.ScheduleModelMapper;
import br.com.challengersicredi.controller.v1.schedule.model.request.ScheduleModelRequest;
import br.com.challengersicredi.impl.schedule.ScheduleService;
import br.com.challengersicredi.impl.schedule.model.response.ScheduleModelImplResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/schedule")
public class ScheduleController {

    ScheduleService service;

    @PostMapping("/save")
    public Mono<ScheduleModelImplResponse> greeting(@RequestBody ScheduleModelRequest scheduleModelRequest) {
        return service.save(ScheduleModelMapper.mapFrom(scheduleModelRequest));
    }

}
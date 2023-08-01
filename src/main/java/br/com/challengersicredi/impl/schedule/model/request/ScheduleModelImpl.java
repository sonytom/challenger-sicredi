package br.com.challengersicredi.impl.schedule.model.request;

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
public class ScheduleModelImpl {
    private String name;
}

package br.com.challengersicredi.controller.v1;

import br.com.challengersicredi.impl.GreetingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GreetingController {

    GreetingService service;
    @GetMapping("/")
    public String greeting() {
     return service.greeting();
    }

}

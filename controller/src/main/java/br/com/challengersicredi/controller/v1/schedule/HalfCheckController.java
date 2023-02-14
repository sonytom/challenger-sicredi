package br.com.challengersicredi.controller.v1.schedule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HalfCheckController {


    @GetMapping("/")
    public String greeting() {
        return "Teste";
    }

}

package br.com.challengersicredi.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class GreetingService {

    public String greeting() {
        return "teste";
    }

}

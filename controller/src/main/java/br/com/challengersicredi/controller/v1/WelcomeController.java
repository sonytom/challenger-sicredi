package br.com.challengersicredi.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/")
    public String welcome() {
        return "Olá Bem vindo. para continuar usando acesse o repositorio para mais informações: https://github.com/sonytom/challenger-sicredi";
    }
}

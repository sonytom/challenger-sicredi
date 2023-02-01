package br.com.challengersicredi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.challengersicredi")
public class ChallengerSicrediApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengerSicrediApplication.class, args);
    }

}

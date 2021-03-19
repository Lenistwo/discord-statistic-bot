package pl.lenistwo.statbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StatBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatBotApplication.class, args);
    }
}

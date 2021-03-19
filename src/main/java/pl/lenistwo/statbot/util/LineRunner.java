package pl.lenistwo.statbot.util;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.lenistwo.statbot.event.BotReadyEvent;

@Component
@RequiredArgsConstructor
public class LineRunner  implements CommandLineRunner {

    private final JDA jda;
    private final BotReadyEvent readyEvent;

    @Override
    public void run(String... args) {
        jda.addEventListener(readyEvent);
    }
}

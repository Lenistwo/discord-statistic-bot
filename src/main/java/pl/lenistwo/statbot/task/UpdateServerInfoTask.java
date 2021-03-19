package pl.lenistwo.statbot.task;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lenistwo.statbot.service.UpdateServerInfoService;

@Component
@RequiredArgsConstructor
public class UpdateServerInfoTask {

    private final UpdateServerInfoService updateServerInfoService;

    @Scheduled(fixedRateString = "${stat.bot.update.rate.in.milliseconds}",
               initialDelayString = "${stat.bot.update.initial.delay.in.milliseconds}")
    public void updateServerStatistic() {
        updateServerInfoService.updateServerInfo();
    }
}

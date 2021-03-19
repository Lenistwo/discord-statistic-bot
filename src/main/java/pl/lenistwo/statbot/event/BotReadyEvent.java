package pl.lenistwo.statbot.event;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.lenistwo.statbot.service.StatisticService;

import javax.annotation.Nonnull;
import java.util.Objects;

import static pl.lenistwo.statbot.util.BotConstants.*;

@Component
@RequiredArgsConstructor
public class BotReadyEvent extends ListenerAdapter {

    @Value("${stat.bot.online.users.channel}")
    private String ONLINE_USERS_ID;

    @Value("${stat.bot.all.users.channel}")
    private String USERS_COUNT_ID;

    @Value("${stat.bot.banned.users.channel}")
    private String BANNED_USERS_ID;

    @Value("${stat.bot.record.online.users.channel}")
    private String ONLINE_RECORD_ID;

    private final StatisticService statisticService;

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        var jda = event.getJDA();
        statisticService.setRecordOnline(getCurrentCount(jda, ONLINE_RECORD_ID));
        statisticService.setUsersOnline(getCurrentCount(jda, ONLINE_USERS_ID));
        statisticService.setAllUsers(getCurrentCount(jda, USERS_COUNT_ID));
        statisticService.setBannedUsers(getCurrentCount(jda, BANNED_USERS_ID));
    }

    private int getCurrentCount(JDA jda, String channelId) {
        return getCount(Objects.requireNonNull(jda.getGuilds().get(FIRST_INDEX).getGuildChannelById(channelId)).getName());
    }

    private int getCount(String channelName) {
        String[] split = channelName.split(COLON);
        try {
            return Integer.parseInt(split[SECOND_INDEX].trim());
        } catch (Exception e) {
            return 0;
        }
    }
}

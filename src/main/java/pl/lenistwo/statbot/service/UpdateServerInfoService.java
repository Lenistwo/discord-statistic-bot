package pl.lenistwo.statbot.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.lenistwo.statbot.http.api.APIClient;
import pl.lenistwo.statbot.model.channel.Channel;
import pl.lenistwo.statbot.model.request.UpdateCall;
import pl.lenistwo.statbot.model.response.ServerInfo;
import pl.lenistwo.statbot.service.api.UpdateServerInfo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static pl.lenistwo.statbot.util.BotConstants.*;

@Service
@RequiredArgsConstructor
public class UpdateServerInfoService implements UpdateServerInfo {

    @Value("${stat.bot.online.users.channel}")
    private String ONLINE_USERS_ID;

    @Value("${stat.bot.all.users.channel}")
    private String USERS_COUNT_ID;

    @Value("${stat.bot.banned.users.channel}")
    private String BANNED_USERS_ID;

    @Value("${stat.bot.record.online.users.channel}")
    private String ONLINE_RECORD_ID;

    private final JDA jda;
    private final Gson gson;
    private final StatisticService statisticService;
    private final APIClient apiClient;


    @Override
    public void updateServerInfo() {
        var updateCalls = new HashSet<UpdateCall>();
        var serverInfo = apiClient.retrieveStatistics();
        var onlineStatistics = statisticService.getOnlineStatistics();

        var banCount = jda.getGuilds().get(FIRST_INDEX).retrieveBanList().complete().size();
        var onlineMembersCount = serverInfo.getApproximatePresenceCount();
        var memberCount = serverInfo.getApproximateMemberCount();

        if (onlineStatistics.getAllUsers() != memberCount) {
            statisticService.setAllUsers(memberCount);
            addUpdateCall(updateCalls, USERS_COUNT_ID, USERS_COUNT_CHANNEL + memberCount);
        }

        if (onlineStatistics.getUsersOnline() != onlineMembersCount) {
            statisticService.setUsersOnline(onlineMembersCount);
            addUpdateCall(updateCalls, ONLINE_USERS_ID, ONLINE_USERS_CHANNEL + onlineMembersCount);
        }

        if (onlineStatistics.getRecordOnline() < onlineMembersCount) {
            statisticService.setRecordOnline(onlineMembersCount);
            addUpdateCall(updateCalls, ONLINE_RECORD_ID, ONLINE_RECORD_CHANNEL + onlineMembersCount);
        }

        if (onlineStatistics.getBannedUsers() != banCount) {
            statisticService.setBannedUsers(banCount);
            addUpdateCall(updateCalls, BANNED_USERS_ID, BANNED_USERS_CHANNEL + banCount);
        }

        invokeUpdateCall(updateCalls);
    }

    private void addUpdateCall(Set<UpdateCall> updateCalls, String channelId, String channelName) {
        var channel = jda.getVoiceChannelById(channelId);
        var updatedChannel = gson.toJson(setChannelName(channel, channelName));
        updateCalls.add(new UpdateCall(Objects.requireNonNull(channel).getId(), updatedChannel));
    }

    private void invokeUpdateCall(Set<UpdateCall> calls) {
        CompletableFuture.runAsync(() -> calls.forEach(updateCall -> apiClient.sendChannelUpdate(updateCall.getId(), updateCall.getUpdatedChannel())));
    }

    private Channel setChannelName(VoiceChannel channel, String name) {
        var updated = new Channel(channel);
        updated.setName(name);
        return updated;
    }
}

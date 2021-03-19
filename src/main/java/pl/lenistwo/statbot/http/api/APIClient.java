package pl.lenistwo.statbot.http.api;

import pl.lenistwo.statbot.model.response.ServerInfo;

public interface APIClient {
    ServerInfo retrieveStatistics();
    void sendChannelUpdate(String id, String body);
}

package pl.lenistwo.statbot.http;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import pl.lenistwo.statbot.exception.PatchRequestException;
import pl.lenistwo.statbot.exception.GetRequestException;
import pl.lenistwo.statbot.http.api.APIClient;
import pl.lenistwo.statbot.model.response.ServerInfo;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class StatApiClient implements APIClient {

    public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

    private final String BASE_URL;
    private final String GUILD_ID;

    private final Gson gson;
    private final OkHttpClient okHttpClient;


    @Override
    public ServerInfo retrieveStatistics() {
        return sendGetRequest(String.format("%s/guilds/%s/preview", BASE_URL, GUILD_ID), ServerInfo.class);
    }

    @Override
    public void sendChannelUpdate(String id, String channel) {
        var requestBody = RequestBody.create(MediaType.parse(CONTENT_TYPE_JSON), channel);
        sendPatchRequest(String.format("%s/channels/%s", BASE_URL, id), requestBody);
    }

    private void sendPatchRequest(String url, RequestBody requestBody) {
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).patch(requestBody).build();
        try (var response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new PatchRequestException(url);
            }
        } catch (IOException e) {
            throw new PatchRequestException(url);
        }
    }

    private <T> T sendGetRequest(String url, Class<T> objectType) {
        var builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        try (var response = okHttpClient.newCall(request).execute()) {
            return gson.fromJson(Objects.requireNonNull(response.body()).string(), objectType);
        } catch (IOException e) {
            throw new GetRequestException(objectType, e);
        }
    }

}

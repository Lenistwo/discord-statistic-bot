package pl.lenistwo.statbot.http;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthInterceptor implements Interceptor {
    public static final String API_AUTH_TOKEN_HEADER = "Authorization";
    public static final String BOT_QUALIFIER = "Bot ";

    public final String API_TOKEN;

    @Override
    public Response intercept(Chain chain) throws IOException {
        var authRequest = chain.request().newBuilder().header(API_AUTH_TOKEN_HEADER, BOT_QUALIFIER + API_TOKEN).build();
        return chain.proceed(authRequest);
    }
}

package pl.lenistwo.statbot.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lenistwo.statbot.http.AuthInterceptor;
import pl.lenistwo.statbot.http.StatApiClient;
import pl.lenistwo.statbot.http.api.APIClient;

@Configuration
class BotConfig {

    @Value("${stat.bot.token}")
    private String BOT_TOKEN;

    @Value("${stat.bot.api.url}")
    private String BASE_URL;

    @Value("${stat.bot.guild.id}")
    private String GUILD_ID;

    @Bean
    @SneakyThrows
    public JDA jda() {
        return JDABuilder.createDefault(BOT_TOKEN).build();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Bean
    public APIClient statApi(Gson gson) {
        var okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new AuthInterceptor(BOT_TOKEN)).build();
        return new StatApiClient(BASE_URL, GUILD_ID, gson, okHttpClient);
    }
}

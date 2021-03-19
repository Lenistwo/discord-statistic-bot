package pl.lenistwo.statbot.model.channel;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Channel {
    private List<PermissionOverwrites> permissionOverwrites;
    private boolean nsfw;
    private String parentId;
    private String name;
    private String guildI;
    private String bitrate;
    private String id;
    private int position;
    private int type;
    private int userLimit;

    public Channel(VoiceChannel channel) {
        this.nsfw = false;
        this.parentId = Objects.requireNonNull(channel.getParent()).getId();
        this.name = channel.getName();
        this.guildI = channel.getGuild().getId();
        this.bitrate = String.valueOf(channel.getBitrate());
        this.id = channel.getId();
        this.position = channel.getPosition();
        this.type = channel.getType().getId();
        this.userLimit = channel.getUserLimit();
        this.permissionOverwrites = new ArrayList<>();
        channel.getPermissionOverrides().forEach(permission -> {
            PermissionOverwrites permissionoverwrites = new PermissionOverwrites();
            permissionoverwrites.setId(permission.getId());
            permissionoverwrites.setType("role");
            permissionoverwrites.setAllow("1024");
            permissionoverwrites.setDeny("2147482615");
            this.permissionOverwrites.add(permissionoverwrites);
        });
    }
}


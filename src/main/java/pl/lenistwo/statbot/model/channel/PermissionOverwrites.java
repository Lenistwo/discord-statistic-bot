package pl.lenistwo.statbot.model.channel;

import lombok.Data;

@Data
public class PermissionOverwrites {
    private String id;
    private String allow;
    private String deny;
    private String type;
}

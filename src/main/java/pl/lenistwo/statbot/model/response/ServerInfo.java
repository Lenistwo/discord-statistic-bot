package pl.lenistwo.statbot.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ServerInfo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("splash")
    @Expose
    private Object splash;
    @SerializedName("discovery_splash")
    @Expose
    private Object discoverySplash;
    @SerializedName("features")
    @Expose
    private List<String> features = null;
    @SerializedName("approximate_member_count")
    @Expose
    private Integer approximateMemberCount;
    @SerializedName("approximate_presence_count")
    @Expose
    private Integer approximatePresenceCount;
}


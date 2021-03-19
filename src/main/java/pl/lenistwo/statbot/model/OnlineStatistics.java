package pl.lenistwo.statbot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineStatistics {
    private int allUsers;
    private int recordOnline;
    private int usersOnline;
    private int bannedUsers;
}

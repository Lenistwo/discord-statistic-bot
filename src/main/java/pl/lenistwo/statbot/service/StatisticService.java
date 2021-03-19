package pl.lenistwo.statbot.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.lenistwo.statbot.model.OnlineStatistics;

@Data
@Service
public class StatisticService {

    private OnlineStatistics onlineStatistics;

    public StatisticService() {
        this.onlineStatistics = new OnlineStatistics();
    }

    public void setRecordOnline(int recordOnline) {
        this.onlineStatistics.setRecordOnline(recordOnline);
    }

    public void setUsersOnline(int usersOnline) {
        this.onlineStatistics.setUsersOnline(usersOnline);
    }

    public void setAllUsers(int allUsers) {
        this.onlineStatistics.setAllUsers(allUsers);
    }

    public void setBannedUsers(int bannedUsers) {
        this.onlineStatistics.setBannedUsers(bannedUsers);
    }
}

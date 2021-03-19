package pl.lenistwo.statbot.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCall {
    private String id;
    private String updatedChannel;
}

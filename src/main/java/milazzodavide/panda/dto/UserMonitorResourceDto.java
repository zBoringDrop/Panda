package milazzodavide.panda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import milazzodavide.panda.type.ResourceType;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserMonitorResourceDto {
    private Long userMonitorId;
    private Long userId;
    private Long targetResourceId;
    private String name;
    private boolean enabled;
    private String description;
    private String notes;
    private ResourceType resourceType;

    private String address;
    private int port;
}

package milazzodavide.panda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import milazzodavide.panda.type.ResourceType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserMonitorDto {
    private Long id;
    private Long userId;
    private Long targetResourceId;
    private String name;
    private boolean enabled;
    private String description;
    private String notes;
    private ResourceType resourceType;
}

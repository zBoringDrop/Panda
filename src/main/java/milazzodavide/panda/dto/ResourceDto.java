package milazzodavide.panda.dto;

import lombok.*;
import milazzodavide.panda.type.ResourceType;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {
    private Long id;
    private Long userId;
    private String name;
    private String ipAddress;
    private int port;
    private boolean enabled;
    private ResourceType resourceType;
}

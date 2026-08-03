package milazzodavide.panda.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TargetResourceDto {
    private Long id;
    private String address;
    private int port;
}

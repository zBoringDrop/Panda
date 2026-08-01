package milazzodavide.panda.dto;
import lombok.*;
import milazzodavide.panda.role.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
}

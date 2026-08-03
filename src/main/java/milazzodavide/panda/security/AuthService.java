package milazzodavide.panda.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milazzodavide.panda.dto.UserDto;
import milazzodavide.panda.entity.UserEntity;
import milazzodavide.panda.exception.ExceptionMessage;
import milazzodavide.panda.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public String register(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        UserDto newDto = userService.create(userDto);

        UserDetails userDetails = userDetailsService.loadUserByUsername(newDto.getEmail());

        return jwtService.generateToken(userDetails);
    }

    public Long getIdFromLogin(Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        if (userEntity == null) {
            throw new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND_FROM_EMAIL);
        }
        return userEntity.getId();
    }
}

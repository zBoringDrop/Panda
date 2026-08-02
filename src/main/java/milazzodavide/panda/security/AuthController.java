package milazzodavide.panda.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milazzodavide.panda.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> create(@RequestBody UserDto userDto) {
        String jwtToken = authService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDto(jwtToken));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponseDto(jwtToken));
    }
}
package milazzodavide.panda.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milazzodavide.panda.dto.UserDto;
import milazzodavide.panda.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> create(@PathVariable Long userId) {
        log.info("Received new user get request by id {}", userId);
        UserDto userDto = userService.findById(userId);
        log.info("User found: {}", userDto);

        return ResponseEntity.ok(userDto);
    }
}

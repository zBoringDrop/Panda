package milazzodavide.panda.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milazzodavide.panda.dto.UserMonitorDto;
import milazzodavide.panda.dto.UserMonitorResourceDto;
import milazzodavide.panda.security.AuthService;
import milazzodavide.panda.service.UserMonitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/resourcemonitor")
@RequiredArgsConstructor
@Slf4j
public class UserMonitorController {

    private final UserMonitorService monitorService;
    private final AuthService authService;

    @PostMapping("/link")
    public ResponseEntity<UserMonitorDto> createAndLink(@RequestBody UserMonitorResourceDto monitorResourceDto,
                                                        Authentication authentication) {
        Long userId = authService.getIdFromLogin(authentication);
        monitorResourceDto.setUserId(userId);
        log.info("Received new createAndLink request by user {}: {}", userId, monitorResourceDto);
        return ResponseEntity.ok(monitorService.addResourceAndLinkUser(monitorResourceDto));
    }

    @PostMapping("/unlink")
    public ResponseEntity<Long> unLink(@RequestBody Long monitorId,
                                       Authentication authentication) {
        Long userId = authService.getIdFromLogin(authentication);
        log.info("Received new unLink request by user {}: {}", userId, monitorId);
        monitorService.unlink(userId, monitorId);
        return ResponseEntity.ok(1L);
    }
}

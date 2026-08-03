package milazzodavide.panda.scheduler;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milazzodavide.panda.dto.TargetResourceDto;
import milazzodavide.panda.dto.UptimeHistoryDto;
import milazzodavide.panda.network_utils.PingResponse;
import milazzodavide.panda.network_utils.PingService;
import milazzodavide.panda.service.TargetResourceService;
import milazzodavide.panda.service.UptimeHistoryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UptimeScheduler {

    private final UptimeHistoryService historyService;
    private final TargetResourceService resourceService;

    @Scheduled(fixedDelayString = "${scheduler.uptime.checker.delay:20000}")
    private void checkResourceStatus() {
        log.info("Getting all enabled resources to ping...");
        List<TargetResourceDto> resourceDtos = resourceService.findAll();
        log.info("Founded enabled resources: {}", resourceDtos);

        for (TargetResourceDto resource : resourceDtos) {
            PingResponse pingResponse = PingService.ping(resource.getAddress(), resource.getPort());
            historyService.create(new UptimeHistoryDto(null, resource.getId(), pingResponse.status(), pingResponse.latency(), pingResponse.checkedAt()));
        }
    }

}

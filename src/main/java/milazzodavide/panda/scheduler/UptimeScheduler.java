package milazzodavide.panda.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milazzodavide.panda.dto.ResourceDto;
import milazzodavide.panda.dto.UptimeHistoryDto;
import milazzodavide.panda.network_utils.PingResponse;
import milazzodavide.panda.network_utils.PingService;
import milazzodavide.panda.service.ResourceService;
import milazzodavide.panda.service.UptimeHistoryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class UptimeScheduler {

    private final UptimeHistoryService historyService;
    private final ResourceService resourceService;

    @Scheduled(fixedDelay = 20000)
    private void checkResourceStatus() {
        log.info("Getting all enabled resources to ping...");
        List<ResourceDto> resourceDtos = resourceService.findAllEnabled(true);
        log.info("Founded enabled resources: {}", resourceDtos);

        HashMap<String, PingResponse> userIpPortMap = new HashMap<>();

        for (ResourceDto resource : resourceDtos) {
            final String key = resource.getIpAddress() + ":" + resource.getPort();
            if (!userIpPortMap.containsKey(key)) {
                log.info("New resource [{}]: {}", key, resource);
                PingResponse pingResponse = PingService.ping(resource.getIpAddress(), resource.getPort());
                log.info("Ping response: {}", pingResponse);
                userIpPortMap.put(key, pingResponse);

                historyService.create(new UptimeHistoryDto(null, resource.getId(), pingResponse.status(), pingResponse.latency(), pingResponse.checkedAt()));
            } else {
                log.info("Resource already ping [{}]: {}", key, resource);
                PingResponse resourceAlreadyPing = userIpPortMap.get(key);
                historyService.create(new UptimeHistoryDto(null, resource.getId(), resourceAlreadyPing.status(), resourceAlreadyPing.latency(), resourceAlreadyPing.checkedAt()));
            }

        }

    }

}

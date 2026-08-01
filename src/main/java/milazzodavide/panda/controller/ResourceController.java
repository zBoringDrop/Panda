package milazzodavide.panda.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milazzodavide.panda.dto.ResourceDto;
import milazzodavide.panda.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/resource")
@RequiredArgsConstructor
@Slf4j
public class ResourceController {

    final ResourceService resourceService;

    @PostMapping("/create")
    ResponseEntity<ResourceDto> create(@RequestBody ResourceDto dto) {
        log.info("Received new resource add request {}", dto);
        ResourceDto newDto = resourceService.create(dto);
        log.info("New resource created: {}", newDto);

        return ResponseEntity.ok(newDto);
    }

    @GetMapping("/get/{resourceId}")
    ResponseEntity<ResourceDto> findById(@PathVariable Long resourceId) {
        log.info("Received new resource get request for id {}", resourceId);
        ResourceDto resourceDto = resourceService.findById(resourceId);
        log.info("Found resource: {}", resourceDto);

        return ResponseEntity.ok(resourceDto);
    }

    @GetMapping("/get/all")
    ResponseEntity<List<ResourceDto>> findAll() {
        log.info("Received new resource get request");
        List<ResourceDto> resourceDto = resourceService.findAll();
        log.info("Found resource: {}", resourceDto);

        return ResponseEntity.ok(resourceDto);
    }
}

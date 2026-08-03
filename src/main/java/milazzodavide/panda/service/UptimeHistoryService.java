package milazzodavide.panda.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milazzodavide.panda.dao.UptimeHistoryDao;
import milazzodavide.panda.dto.UptimeHistoryDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UptimeHistoryService {

    private final UptimeHistoryDao dao;

    public UptimeHistoryDto create(UptimeHistoryDto dto) {
        return dao.create(dto);
    }

    public List<UptimeHistoryDto> findHistoryByUserAndResource(Long id, String ipAddress, Integer port) {
        return dao.findHistoryByUserAndResource(id, ipAddress, port);
    }

    public List<UptimeHistoryDto> findHistoryByUserAndResourceInDateRange(Long id, String ipAddress, Integer port, LocalDateTime startDate, LocalDateTime endDate) {
        return dao.findHistoryByUserAndResourceInDateRange(id, ipAddress, port, startDate, endDate);
    }

}

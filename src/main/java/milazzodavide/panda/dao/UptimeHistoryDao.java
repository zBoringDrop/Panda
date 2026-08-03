package milazzodavide.panda.dao;

import milazzodavide.panda.dto.UptimeHistoryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface UptimeHistoryDao {
    UptimeHistoryDto create(UptimeHistoryDto dto);
    List<UptimeHistoryDto> findHistoryByUserAndResource(Long id, String ipAddress, Integer port);
    List<UptimeHistoryDto> findHistoryByUserAndResourceInDateRange(Long id, String ipAddress, Integer port, LocalDateTime startDate, LocalDateTime endDate);
}

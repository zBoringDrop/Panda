package milazzodavide.panda.dao;

import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dto.UptimeHistoryDto;
import milazzodavide.panda.entity.UptimeHistoryEntity;
import milazzodavide.panda.mapper.UptimeHistoryMapper;
import milazzodavide.panda.repository.UptimeHistoryRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UptimeHistoryDataAccess implements UptimeHistoryDao {

    private final UptimeHistoryRepository repository;

    @Override
    public UptimeHistoryDto create(UptimeHistoryDto dto) {
        UptimeHistoryEntity newHistory = repository.save(UptimeHistoryMapper.INSTANCE.toEntity(dto));
        return UptimeHistoryMapper.INSTANCE.toDto(newHistory);
    }

    @Override
    public List<UptimeHistoryDto> findAll() {
        return UptimeHistoryMapper.INSTANCE.toDtoList(repository.findAll());
    }

    @Override
    public List<UptimeHistoryDto> findByEnabledResources(boolean isEnabled) {
        return UptimeHistoryMapper.INSTANCE.toDtoList(repository.findByResourceEntity_Enabled(isEnabled));
    }

    @Override
    public List<UptimeHistoryDto> findHistoryByUserAndResource(Long id, String ipAddress, Integer port) {
        return UptimeHistoryMapper.INSTANCE.toDtoList(repository.findHistoryByUserAndResource(id, ipAddress, port));
    }

    @Override
    public List<UptimeHistoryDto> findHistoryByUserAndResourceInDateRange(Long id, String ipAddress, Integer port, LocalDateTime startDate, LocalDateTime endDate) {
        return UptimeHistoryMapper.INSTANCE.toDtoList(repository.findHistoryByUserAndResourceInDateRange(id, ipAddress, port, startDate, endDate));
    }
}

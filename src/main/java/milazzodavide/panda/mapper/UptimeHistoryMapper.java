package milazzodavide.panda.mapper;

import milazzodavide.panda.dto.UptimeHistoryDto;
import milazzodavide.panda.entity.UptimeHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UptimeHistoryMapper {

    UptimeHistoryMapper INSTANCE = Mappers.getMapper(UptimeHistoryMapper.class);

    UptimeHistoryDto toDto(UptimeHistoryEntity entity);
    UptimeHistoryEntity toEntity(UptimeHistoryDto dto);

    List<UptimeHistoryDto> toDtoList(List<UptimeHistoryEntity> entities);
}

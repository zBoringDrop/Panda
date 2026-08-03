package milazzodavide.panda.mapper;

import milazzodavide.panda.dto.UptimeHistoryDto;
import milazzodavide.panda.entity.TargetResourceEntity;
import milazzodavide.panda.entity.UptimeHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UptimeHistoryMapper {

    UptimeHistoryMapper INSTANCE = Mappers.getMapper(UptimeHistoryMapper.class);

    @Mapping(source = "targetResourceEntity.id", target = "resourceEntityId")
    UptimeHistoryDto toDto(UptimeHistoryEntity entity);

    @Mapping(source = "resourceEntityId", target = "targetResourceEntity.id")
    UptimeHistoryEntity toEntity(UptimeHistoryDto dto);

    List<UptimeHistoryDto> toDtoList(List<UptimeHistoryEntity> entities);

    default TargetResourceEntity mapTargetResourceIdToEntity(Long id) {
        if (id == null) {
            return null;
        }
        TargetResourceEntity targetResource = new TargetResourceEntity();
        targetResource.setId(id);
        return targetResource;
    }
}

package milazzodavide.panda.mapper;

import milazzodavide.panda.dto.ResourceDto;
import milazzodavide.panda.entity.ResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourceMapper {

    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    ResourceDto toDto(ResourceEntity entity);
    List<ResourceDto> toDtoList(List<ResourceEntity> entities);

    ResourceEntity toEntity(ResourceDto dto);

}

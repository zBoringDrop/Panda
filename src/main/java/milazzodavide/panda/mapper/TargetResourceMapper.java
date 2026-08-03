package milazzodavide.panda.mapper;

import milazzodavide.panda.dto.TargetResourceDto;
import milazzodavide.panda.entity.TargetResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TargetResourceMapper {

    TargetResourceMapper INSTANCE = Mappers.getMapper(TargetResourceMapper.class);

    TargetResourceDto toDto(TargetResourceEntity entity);
    List<TargetResourceDto> toDtoList(List<TargetResourceEntity> entities);
    TargetResourceEntity toEntity(TargetResourceDto dto);

}

package milazzodavide.panda.mapper;

import milazzodavide.panda.dto.UserMonitorDto;
import milazzodavide.panda.entity.TargetResourceEntity;
import milazzodavide.panda.entity.UserEntity;
import milazzodavide.panda.entity.UserMonitorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMonitorMapper {
    UserMonitorMapper INSTANCE = Mappers.getMapper(UserMonitorMapper.class);

    @Mapping(source = "userEntity.id", target = "userId")
    @Mapping(source = "targetResourceEntity.id", target = "targetResourceId")
    UserMonitorDto toDto(UserMonitorEntity entity);

    List<UserMonitorDto> toDtoList(List<UserMonitorEntity> entityList);

    @Mapping(source = "userId", target = "userEntity.id")
    @Mapping(source = "targetResourceId", target = "targetResourceEntity.id")
    UserMonitorEntity toEntity(UserMonitorDto dto);

    default TargetResourceEntity mapTargetResourceIdToEntity(Long id) {
        if (id == null) {
            return null;
        }
        TargetResourceEntity targetResource = new TargetResourceEntity();
        targetResource.setId(id);
        return targetResource;
    }

    default UserEntity mapUserIdToEntity(Long id) {
        if (id == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        return userEntity;
    }
}

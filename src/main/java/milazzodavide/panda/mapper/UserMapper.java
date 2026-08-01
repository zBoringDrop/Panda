package milazzodavide.panda.mapper;

import milazzodavide.panda.dto.UserDto;
import milazzodavide.panda.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity entity);
    UserEntity toEntity(UserDto dto);
}

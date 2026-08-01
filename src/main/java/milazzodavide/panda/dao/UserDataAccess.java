package milazzodavide.panda.dao;

import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dto.UserDto;
import milazzodavide.panda.entity.UserEntity;
import milazzodavide.panda.exception.ExceptionMessage;
import milazzodavide.panda.exception.IdNotFoundException;
import milazzodavide.panda.mapper.UserMapper;
import milazzodavide.panda.repository.UserRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserDataAccess implements UserDao {

    private final UserRepository repository;

    @Override
    public UserDto create(UserDto dto) {
        UserEntity newUser = repository.save(UserMapper.INSTANCE.toEntity(dto));
        return UserMapper.INSTANCE.toDto(newUser);
    }

    @Override
    public UserDto findById(Long id) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ExceptionMessage.ID_NOT_FOUND));
        return UserMapper.INSTANCE.toDto(userEntity);
    }
}

package milazzodavide.panda.dao;

import milazzodavide.panda.dto.UserDto;

public interface UserDao {
    UserDto create(UserDto dto);
    UserDto findById(Long id);
}

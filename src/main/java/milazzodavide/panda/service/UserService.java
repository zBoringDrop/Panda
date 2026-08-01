package milazzodavide.panda.service;

import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dao.UserDao;
import milazzodavide.panda.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserDao dao;

    public UserDto create(UserDto dto) {
        UserDto newDto = dao.create(dto);
        newDto.setPassword("");
        return newDto;
    }

    public UserDto findById(Long id) {
        UserDto userDto = dao.findById(id);
        userDto.setPassword("");
        return userDto;
    }
}

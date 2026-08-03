package milazzodavide.panda.dao;

import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dto.UserMonitorDto;
import milazzodavide.panda.entity.UserMonitorEntity;
import milazzodavide.panda.mapper.UserMonitorMapper;
import milazzodavide.panda.repository.UserMonitorRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserMonitorDataAccess implements UserMonitorDao {

    private final UserMonitorRepository repository;

    @Override
    public UserMonitorDto create(UserMonitorDto dto) {
        UserMonitorEntity entity = repository.save(UserMonitorMapper.INSTANCE.toEntity(dto));
        return UserMonitorMapper.INSTANCE.toDto(entity);
    }
}

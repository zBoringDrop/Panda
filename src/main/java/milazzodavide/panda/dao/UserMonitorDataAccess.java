package milazzodavide.panda.dao;

import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dto.UserMonitorDto;
import milazzodavide.panda.entity.UserMonitorEntity;
import milazzodavide.panda.exception.ExceptionMessage;
import milazzodavide.panda.exception.IdNotFoundException;
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

    @Override
    public UserMonitorDto findById(Long monitorId) {
        UserMonitorEntity entity = repository.findById(monitorId)
                .orElseThrow(() -> new IdNotFoundException(ExceptionMessage.ID_NOT_FOUND));
        return UserMonitorMapper.INSTANCE.toDto(entity);
    }

    @Override
    public boolean userAlreadyLinked(Long userId, String address, int port) {
        return !repository.userAlreadyLinked(userId, address, port).isEmpty();
    }

    @Override
    public boolean existsByTargetResourceId(Long resourceId) {
        return repository.existsByTargetResourceEntity_id(resourceId);
    }

    @Override
    public void delete(Long monitorId) {
        UserMonitorEntity entityToDelete = repository.findById(monitorId)
                .orElseThrow(() -> new IdNotFoundException(ExceptionMessage.ID_NOT_FOUND));
        repository.delete(entityToDelete);
    }
}

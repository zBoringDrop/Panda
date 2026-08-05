package milazzodavide.panda.dao;

import milazzodavide.panda.dto.UserMonitorDto;

public interface UserMonitorDao {
    UserMonitorDto create(UserMonitorDto dto);
    UserMonitorDto findById(Long monitorId);
    boolean userAlreadyLinked(Long userId, String address, int port);
    boolean existsByTargetResourceId(Long resourceId);
    void delete(Long monitorId);
}

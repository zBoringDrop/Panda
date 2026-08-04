package milazzodavide.panda.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dao.TargetResourceDao;
import milazzodavide.panda.dao.UptimeHistoryDao;
import milazzodavide.panda.dao.UserMonitorDao;
import milazzodavide.panda.dto.TargetResourceDto;
import milazzodavide.panda.dto.UptimeHistoryDto;
import milazzodavide.panda.dto.UserMonitorDto;
import milazzodavide.panda.dto.UserMonitorResourceDto;
import milazzodavide.panda.exception.ExceptionMessage;
import milazzodavide.panda.exception.NotTheOwnerException;
import milazzodavide.panda.exception.ResourceIpPortAlreadyAddedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMonitorService {

    private final UserMonitorDao monitorDao;
    private final TargetResourceDao resourceDao;
    private final UptimeHistoryDao historyDao;

    @Transactional
    public UserMonitorDto addResourceAndLinkUser(UserMonitorResourceDto dto) {
        if (monitorDao.userAlreadyLinked(dto.getUserId(), dto.getAddress(), dto.getPort())) {
            throw new ResourceIpPortAlreadyAddedException(ExceptionMessage.RESOURCE_IP_PORT_ALREADY_ADDED);
        }

        TargetResourceDto targetDto = getTargetResourceDto(dto);
        TargetResourceDto secureTarget = resourceDao.findOrCreate(targetDto);

        UserMonitorDto userMonitorDto = getUserMonitorDto(dto);
        userMonitorDto.setTargetResourceId(secureTarget.getId());

        return monitorDao.create(userMonitorDto);
    }

    @Transactional
    public void unlink(long userId, long monitorId) {
        UserMonitorDto monitorDto = monitorDao.findById(monitorId);
        if (monitorDto.getUserId() != userId) {
            throw new NotTheOwnerException(ExceptionMessage.NOT_THE_OWNER);
        }
        monitorDao.delete(monitorId);
        if (!monitorDao.existsByTargetResourceId(monitorDto.getTargetResourceId())) {
            historyDao.deleteByTargetResourceId(monitorDto.getTargetResourceId());
            resourceDao.delete(monitorDto.getTargetResourceId());
        }
    }

    UserMonitorDto getUserMonitorDto(UserMonitorResourceDto dto) {
        return new UserMonitorDto(null, dto.getUserId(), dto.getTargetResourceId(), dto.getName(), dto.isEnabled(), dto.getDescription(), dto.getNotes(), dto.getResourceType());
    }

    TargetResourceDto getTargetResourceDto(UserMonitorResourceDto dto) {
        return new TargetResourceDto(null, dto.getAddress(), dto.getPort());
    }
}

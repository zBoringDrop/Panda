package milazzodavide.panda.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dao.TargetResourceDao;
import milazzodavide.panda.dao.UserMonitorDao;
import milazzodavide.panda.dto.TargetResourceDto;
import milazzodavide.panda.dto.UserMonitorDto;
import milazzodavide.panda.dto.UserMonitorResourceDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMonitorService {

    private final UserMonitorDao monitorDao;
    private final TargetResourceDao resourceDao;

    @Transactional
    public UserMonitorDto addResourceAndLinkUser(UserMonitorResourceDto dto) {
        TargetResourceDto targetDto = getTargetResourceDto(dto);
        TargetResourceDto secureTarget = resourceDao.findOrCreate(targetDto);

        UserMonitorDto userMonitorDto = getUserMonitorDto(dto);
        userMonitorDto.setTargetResourceId(secureTarget.getId());

        return monitorDao.create(userMonitorDto);
    }

    UserMonitorDto getUserMonitorDto(UserMonitorResourceDto dto) {
        return new UserMonitorDto(null, dto.getUserId(), dto.getTargetResourceId(), dto.getName(), dto.isEnabled(), dto.getDescription(), dto.getNotes(), dto.getResourceType());
    }

    TargetResourceDto getTargetResourceDto(UserMonitorResourceDto dto) {
        return new TargetResourceDto(null, dto.getAddress(), dto.getPort());
    }
}

package milazzodavide.panda.service;

import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dao.TargetResourceDao;
import milazzodavide.panda.dto.TargetResourceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TargetResourceService {

    private final TargetResourceDao dao;

    public TargetResourceDto create(TargetResourceDto dto) {
        return dao.create(dto);
    }

    public List<TargetResourceDto> findAll() {
        return dao.findAll();
    }

    public Boolean existsByAddressAndPort(String address, int port) {
        return dao.existsByAddressAndPort(address, port);
    }

    public TargetResourceDto findByAddressAndPort(String address, int port) {
        return dao.findByAddressAndPort(address, port);
    }

    public TargetResourceDto findOrCreate(TargetResourceDto dto) {
        return dao.findOrCreate(dto);
    }

}

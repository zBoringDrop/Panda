package milazzodavide.panda.dao;

import milazzodavide.panda.dto.TargetResourceDto;

import java.util.List;

public interface TargetResourceDao {
    TargetResourceDto create(TargetResourceDto dto);
    List<TargetResourceDto> findAll();
    boolean existsByAddressAndPort(String address, int port);
    TargetResourceDto findByAddressAndPort(String address, int port);
    TargetResourceDto findOrCreate(TargetResourceDto dto);
}

package milazzodavide.panda.dao;

import milazzodavide.panda.dto.ResourceDto;

import java.util.List;

public interface ResourceDao {
    ResourceDto create(ResourceDto dto);
    ResourceDto findById(Long id);
    List<ResourceDto> findAll();
    List<ResourceDto> findAllByUserId(Long id);
    List<ResourceDto> findAllEnabled(boolean enabled);
    void setEnabled(Long id, boolean enabled);
}

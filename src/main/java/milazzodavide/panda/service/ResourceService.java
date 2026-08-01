package milazzodavide.panda.service;

import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dao.ResourceDao;
import milazzodavide.panda.dto.ResourceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceDao dao;

    public ResourceDto create(ResourceDto dto) {
        return dao.create(dto);
    }

    public ResourceDto findById(Long id) {
        return dao.findById(id);
    }

    public List<ResourceDto> findAll() {
        return dao.findAll();
    }

    public List<ResourceDto> findAllByUserId(Long id) {
        return dao.findAllByUserId(id);
    }

    public List<ResourceDto> findAllEnabled(boolean enabled) {
        return dao.findAllEnabled(enabled);
    }

    public void setEnabled(Long id, boolean enabled) {
        dao.setEnabled(id, enabled);
    }
}

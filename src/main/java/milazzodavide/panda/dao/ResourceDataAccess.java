package milazzodavide.panda.dao;

import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dto.ResourceDto;
import milazzodavide.panda.entity.ResourceEntity;
import milazzodavide.panda.exception.ExceptionMessage;
import milazzodavide.panda.exception.IdNotFoundException;
import milazzodavide.panda.mapper.ResourceMapper;
import milazzodavide.panda.repository.ResourceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResourceDataAccess implements ResourceDao {

    private final ResourceRepository repository;

    @Override
    public ResourceDto create(ResourceDto dto) {
        ResourceEntity newResource = repository.save(ResourceMapper.INSTANCE.toEntity(dto));
        return ResourceMapper.INSTANCE.toDto(newResource);
    }

    @Override
    public ResourceDto findById(Long id) {
        ResourceEntity entity = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ExceptionMessage.ID_NOT_FOUND));
        return ResourceMapper.INSTANCE.toDto(entity);
    }

    @Override
    public List<ResourceDto> findAll() {
        return ResourceMapper.INSTANCE.toDtoList(repository.findAll());
    }

    @Override
    public List<ResourceDto> findAllByUserId(Long id) {
        return ResourceMapper.INSTANCE.toDtoList(repository.findByUserEntity_id(id));
    }

    @Override
    public List<ResourceDto> findAllEnabled(boolean enabled) {
        return ResourceMapper.INSTANCE.toDtoList(repository.findByEnabled(enabled));
    }

    @Override
    public void setEnabled(Long id, boolean isEnabled) {
        ResourceEntity entity = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ExceptionMessage.ID_NOT_FOUND));

        entity.setEnabled(isEnabled);
        repository.save(entity);
    }
}

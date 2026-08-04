package milazzodavide.panda.dao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import milazzodavide.panda.dto.TargetResourceDto;
import milazzodavide.panda.entity.TargetResourceEntity;
import milazzodavide.panda.exception.ExceptionMessage;
import milazzodavide.panda.exception.IdNotFoundException;
import milazzodavide.panda.exception.ResourceIpPortAlreadyAddedException;
import milazzodavide.panda.mapper.TargetResourceMapper;
import milazzodavide.panda.repository.TargetResourceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TargetResourceDataAccess implements TargetResourceDao {

    private final TargetResourceRepository repository;

    @Override
    public TargetResourceDto create(TargetResourceDto dto) {
        TargetResourceEntity newEntity = repository.save(TargetResourceMapper.INSTANCE.toEntity(dto));
        return TargetResourceMapper.INSTANCE.toDto(newEntity);
    }

    @Override
    public void delete(Long id) {
        TargetResourceEntity entityToDelete = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ExceptionMessage.ID_NOT_FOUND));
        repository.delete(entityToDelete);
    }

    @Override
    public List<TargetResourceDto> findAll() {
        return TargetResourceMapper.INSTANCE.toDtoList(repository.findAll());
    }

    @Override
    public boolean existsByAddressAndPort(String address, int port) {
        return repository.existsByAddressAndPort(address, port);
    }

    @Override
    public TargetResourceDto findByAddressAndPort(String address, int port) {
        TargetResourceEntity entity = repository.findByAddressAndPort(address, port)
                .orElseThrow(() -> new ResourceIpPortAlreadyAddedException(ExceptionMessage.RESOURCE_IP_PORT_NOT_FOUND));
        return TargetResourceMapper.INSTANCE.toDto(entity);
    }

    @Override
    @Transactional
    public TargetResourceDto findOrCreate(TargetResourceDto dto) {
        TargetResourceEntity entity = repository.findByAddressAndPort(dto.getAddress(), dto.getPort())
                .orElseGet(() -> {
                    TargetResourceEntity newEntity = new TargetResourceEntity();
                    newEntity.setAddress(dto.getAddress());
                    newEntity.setPort(dto.getPort());
                    return repository.save(newEntity);
                });

        return TargetResourceMapper.INSTANCE.toDto(entity);
    }
}

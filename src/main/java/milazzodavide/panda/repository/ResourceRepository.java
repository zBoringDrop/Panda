package milazzodavide.panda.repository;

import milazzodavide.panda.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

    List<ResourceEntity> findByUserEntity_id(Long id);
    List<ResourceEntity> findByEnabled(boolean isEnabled);
}

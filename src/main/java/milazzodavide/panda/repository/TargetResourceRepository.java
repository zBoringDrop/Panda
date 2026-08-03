package milazzodavide.panda.repository;

import milazzodavide.panda.entity.TargetResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetResourceRepository extends JpaRepository<TargetResourceEntity, Long> {
    Boolean existsByAddressAndPort(String address, int port);
    Optional<TargetResourceEntity> findByAddressAndPort(String address, int port);
}

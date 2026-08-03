package milazzodavide.panda.repository;

import milazzodavide.panda.entity.UserMonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMonitorRepository extends JpaRepository<UserMonitorEntity, Long> {
}

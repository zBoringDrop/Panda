package milazzodavide.panda.repository;

import milazzodavide.panda.entity.UserMonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMonitorRepository extends JpaRepository<UserMonitorEntity, Long> {

    @Query("""
        SELECT m
        FROM UserMonitorEntity m
        WHERE m.userEntity.id = :userId
        AND m.targetResourceEntity.address = :address
        AND m.targetResourceEntity.port = :port
    """)
    List<UserMonitorEntity> userAlreadyLinked(
                            @Param("userId") Long userId,
                            @Param("address") String address,
                            @Param("port") int port
    );

    List<UserMonitorEntity> findByTargetResourceEntity_id(Long resourceId);

    boolean existsByTargetResourceEntity_id(Long resourceId);
}

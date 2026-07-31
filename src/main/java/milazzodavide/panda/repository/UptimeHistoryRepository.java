package milazzodavide.panda.repository;

import milazzodavide.panda.entity.UptimeHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UptimeHistoryRepository extends JpaRepository<UptimeHistoryEntity, Long> {

    List<UptimeHistoryEntity> findByResourceEntity_Enabled(boolean isEnabled);

    @Query("""
        SELECT u 
        FROM UptimeHistoryEntity u 
        WHERE u.resourceEntity.userEntity.id = :userId 
          AND u.resourceEntity.ipAddress = :ipAddress 
          AND u.resourceEntity.port = :port
    """)
    List<UptimeHistoryEntity> findHistoryByUserAndResource(
            @Param("userId") Long userId,
            @Param("ipAddress") String ipAddress,
            @Param("port") Integer port
    );

    @Query("""
        SELECT u 
        FROM UptimeHistoryEntity u 
        WHERE u.resourceEntity.userEntity.id = :userId 
          AND u.resourceEntity.ipAddress = :ipAddress 
          AND u.resourceEntity.port = :port
          AND u.checkDate BETWEEN :startDate AND :endDate
    """)
    List<UptimeHistoryEntity> findHistoryByUserAndResourceInDateRange(
            @Param("userId") Long userId,
            @Param("ipAddress") String ipAddress,
            @Param("port") Integer port,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}

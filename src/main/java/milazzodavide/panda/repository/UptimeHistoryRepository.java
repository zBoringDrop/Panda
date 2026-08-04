package milazzodavide.panda.repository;

import milazzodavide.panda.entity.UptimeHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UptimeHistoryRepository extends JpaRepository<UptimeHistoryEntity, Long> {

    @Query("""
        SELECT u 
        FROM UptimeHistoryEntity u 
        WHERE u.targetResourceEntity.address = :address 
          AND u.targetResourceEntity.port = :port
          AND EXISTS (
              SELECT 1 
              FROM UserMonitorEntity um 
              WHERE um.targetResourceEntity = u.targetResourceEntity 
                AND um.userEntity.id = :userId
          )
    """)
    List<UptimeHistoryEntity> findHistoryByUserAndResource(
            @Param("userId") Long userId,
            @Param("address") String address,
            @Param("port") Integer port
    );

    @Query("""
        SELECT u 
        FROM UptimeHistoryEntity u 
        WHERE u.targetResourceEntity.address = :address 
          AND u.targetResourceEntity.port = :port
          AND u.checkDate BETWEEN :startDate AND :endDate
          AND EXISTS (
              SELECT 1 
              FROM UserMonitorEntity um 
              WHERE um.targetResourceEntity = u.targetResourceEntity 
                AND um.userEntity.id = :userId
          )
    """)
    List<UptimeHistoryEntity> findHistoryByUserAndResourceInDateRange(
            @Param("userId") Long userId,
            @Param("address") String address,
            @Param("port") Integer port,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    void deleteByTargetResourceEntity_id(Long resourceId);

    List<UptimeHistoryEntity> findByTargetResourceEntity_id(Long resourceId);
}
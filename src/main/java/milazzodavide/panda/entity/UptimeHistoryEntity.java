package milazzodavide.panda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import milazzodavide.panda.status.Status;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "uptime_history")
public class UptimeHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private ResourceEntity resourceEntity;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "latency_ms", nullable = false)
    private Double latencyMs;

    @Column(name = "check_date", nullable = false)
    private LocalDateTime checkDate;
}

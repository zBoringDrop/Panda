package milazzodavide.panda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import milazzodavide.panda.type.ResourceType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
    name = "user_monitor",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_id_resource",
            columnNames = {"user_id", "target_resource_id"}
        )
    }
)
public class UserMonitorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "target_resource_id", nullable = false)
    private TargetResourceEntity targetResourceEntity;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "resource_type")
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;
}

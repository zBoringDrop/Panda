package milazzodavide.panda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resource")
public class ResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "port", nullable = false)
    private Integer port;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
}

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
@Table(
    name = "target_resource",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_address_port",
            columnNames = {"address", "port"}
        )
    }
)
public class TargetResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "port", nullable = false)
    private Integer port;
}

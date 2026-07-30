package milazzodavide.panda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import milazzodavide.panda.status.Status;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UptimeHistoryDto {
    private Long id;
    private Long resourceEntityId;
    private Status status;
    private Double latencyMs;
    private LocalDateTime checkDate;
}

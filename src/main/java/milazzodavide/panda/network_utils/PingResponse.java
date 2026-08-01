package milazzodavide.panda.network_utils;

import milazzodavide.panda.status.Status;

import java.time.LocalDateTime;

public record PingResponse(Status status, double latency, LocalDateTime checkedAt) {
}

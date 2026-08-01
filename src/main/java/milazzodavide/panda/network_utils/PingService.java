package milazzodavide.panda.network_utils;

import lombok.extern.slf4j.Slf4j;
import milazzodavide.panda.status.Status;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Slf4j
public class PingService {

    public static PingResponse ping(final String IP, final int PORT) {
        long startTime = System.nanoTime();

        try (Socket socket = new Socket(IP, PORT)) {
            double endTime = (System.nanoTime() - startTime) / 1_000_000.0;
            double roundedEndTime = (double) Math.round(endTime * 100) / 100;
            PingResponse pingResponse = new PingResponse(Status.ONLINE, roundedEndTime, LocalDateTime.now());
            log.info("Ping response for [{}:{}]: {}", IP, PORT, pingResponse);
            return pingResponse;
        } catch (UnknownHostException e) {
            log.error("Unknown host: {}", e.toString());
            return new PingResponse(Status.UNKNOWN, 0, LocalDateTime.now());
        } catch (IOException e) {
            log.error("IOException: {}", e.toString());
            return new PingResponse(Status.UNKNOWN, 0, LocalDateTime.now());
        }
    }

}

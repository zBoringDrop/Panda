package milazzodavide.panda.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

@Service
@Slf4j
public class ContainerService {

    public String pingService(final String IP, final int PORT) {
        long startTime = System.nanoTime();

        try (Socket socket = new Socket(IP, PORT)) {
            double endTime = (System.nanoTime() - startTime) / 1_000_000.0;
            double roundedEndTime = (double) Math.round(endTime * 100) / 100;
            return "ONLINE - " + roundedEndTime + "ms";
        } catch (UnknownHostException e) {
            log.error("Unknown host: {}", e.toString());
        } catch (IOException e) {
            log.error("IOException: {}", e.toString());
        }
        return "OFFLINE";
    }
}

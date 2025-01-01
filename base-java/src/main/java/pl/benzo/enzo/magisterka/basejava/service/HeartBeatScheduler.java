package pl.benzo.enzo.magisterka.basejava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.magisterka.basejava.model.HeartbeatRequest;
import pl.benzo.enzo.magisterka.basejava.model.ServiceIdHolder;

@Service
@RequiredArgsConstructor
public class HeartBeatScheduler {
    private final RegistryService registryService;
    private final ServiceIdHolder serviceIdHolder;

    @Scheduled(fixedDelay = 5000)
    public void scheduleHeartbeat() {
            String serviceId = serviceIdHolder.getServiceId();
            if (serviceId != null) {
                try {
                    HeartbeatRequest hbRequest = new HeartbeatRequest(serviceId);
                    registryService.sendHeartbeat(hbRequest);
                    System.out.println("Heartbeat sent");
                } catch (Exception e) {
                    System.err.println("Failed to send heartbeat: " + e.getMessage());
                }
            } else {
                System.err.println("Service ID is not set. Heartbeat not sent.");
            }
    }
}

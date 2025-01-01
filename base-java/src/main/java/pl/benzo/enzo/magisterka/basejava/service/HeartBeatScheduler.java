package pl.benzo.enzo.magisterka.basejava.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.magisterka.basejava.model.HeartbeatRequest;
import pl.benzo.enzo.magisterka.basejava.model.ServiceIdHolder;

@Service
@RequiredArgsConstructor
public class HeartBeatScheduler {
    private final RegistryService registryService;
    private final ThreadPoolTaskScheduler scheduler;
    private ServiceIdHolder serviceIdHolder;

    public void scheduleHeartbeat() {
        scheduler.scheduleAtFixedRate(() -> {
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
        }, 10000);
    }
}

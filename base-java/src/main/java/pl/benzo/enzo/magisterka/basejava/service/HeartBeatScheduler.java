package pl.benzo.enzo.magisterka.basejava.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.magisterka.basejava.model.HeartbeatRequest;
import pl.benzo.enzo.magisterka.basejava.model.ServiceIdHolder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeartBeatScheduler {
    private final RegistryService registryService;
    private final ServiceIdHolder serviceIdHolder;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    @Scheduled(fixedDelay = 5000)
    public void scheduleHeartbeat() {
            executorService.execute(() -> {
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
            });
    }
}

package pl.benzo.enzo.magisterka.basejava.service;

import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.magisterka.basejava.model.DeregisterRequest;
import pl.benzo.enzo.magisterka.basejava.model.RegisterRequest;
import pl.benzo.enzo.magisterka.basejava.model.RegisterResponse;
import pl.benzo.enzo.magisterka.basejava.model.ServiceIdHolder;

@Service
@RequiredArgsConstructor
public class OnInitService {
        private final RegistryService registryService;
        private final ServiceIdHolder serviceIdHolder;
        private String serviceId;

        public void registerService(String applicationName, String serverAddress, int port) {
            RegisterRequest registerRequest = new RegisterRequest(applicationName, serverAddress, port);
            try {
                RegisterResponse registerResponse = registryService.registerService(registerRequest);
                serviceId = registerResponse.getId();
                serviceIdHolder.setServiceId(serviceId);
                System.out.println("Registered with ID: " + serviceId);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        @PreDestroy
        public void deregisterService() {
            if (serviceId != null) {
                try {
                    DeregisterRequest deregisterRequest = new DeregisterRequest(serviceId);
                    registryService.deregisterService(deregisterRequest);
                    System.out.println("Deregistered");
                } catch (Exception e) {
                    System.err.println("Failed to deregister: " + e.getMessage());
                }
            }
        }
}

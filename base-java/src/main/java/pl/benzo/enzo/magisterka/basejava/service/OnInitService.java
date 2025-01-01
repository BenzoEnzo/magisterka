package pl.benzo.enzo.magisterka.basejava.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.magisterka.basejava.model.DeregisterRequest;
import pl.benzo.enzo.magisterka.basejava.model.RegisterRequest;
import pl.benzo.enzo.magisterka.basejava.model.RegisterResponse;
import pl.benzo.enzo.magisterka.basejava.model.ServiceIdHolder;

@Service
@RequiredArgsConstructor
public class OnInitService implements ApplicationListener<WebServerInitializedEvent>  {
        private final RegistryService registryService;
        private final ServiceIdHolder serviceIdHolder;
        private String serviceId;
        private int port;

        @Value("${server.address}")
        private String serverAddress;

        @Value("${spring.application.name}")
        private String applicationName;

        public void registerService() {
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

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.port = event.getWebServer().getPort();
        System.out.println(port);
        registerService();
    }
}

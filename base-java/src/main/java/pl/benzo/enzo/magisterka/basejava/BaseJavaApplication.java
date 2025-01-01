package pl.benzo.enzo.magisterka.basejava;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import pl.benzo.enzo.magisterka.basejava.model.DeregisterRequest;
import pl.benzo.enzo.magisterka.basejava.model.RegisterRequest;
import pl.benzo.enzo.magisterka.basejava.model.RegisterResponse;
import pl.benzo.enzo.magisterka.basejava.model.ServiceIdHolder;
import pl.benzo.enzo.magisterka.basejava.service.RegistryService;
import pl.benzo.enzo.magisterka.basejava.service.ServerPortListener;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class BaseJavaApplication implements CommandLineRunner {

    private final RegistryService registryService;
    private final ServerPortListener serverPortListener;
    private final ServiceIdHolder serviceIdHolder;
    private String serviceId;

    @Value("${server.address}")
    private String serverAddress;

    @Value("${spring.application.name}")
    private String applicationName;

    public static void main(String[] args) {
        SpringApplication.run(BaseJavaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        RegisterRequest registerRequest = new RegisterRequest(applicationName, serverAddress, serverPortListener.getPort());
        try {
            RegisterResponse registerResponse = registryService.registerService(registerRequest);
            serviceId = registerResponse.getId();
            serviceIdHolder.setServiceId(serviceId);
            System.out.println("Registered with ID: " + serviceId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                DeregisterRequest deregisterRequest = new DeregisterRequest(serviceId);
                registryService.deregisterService(deregisterRequest);
                System.out.println("Deregistered");
            } catch (Exception e) {
                System.err.println("Failed to deregister: " + e.getMessage());
            }
        }));
    }
}

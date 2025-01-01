package pl.benzo.enzo.magisterka.basecomponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.benzo.enzo.magisterka.basejava.annotation.BenzoEnzoMagisterkaService;

@SpringBootApplication
@EnableScheduling
@BenzoEnzoMagisterkaService(name = "domain-user-component", address = "localhost", port = 8080)
public class DomainUserComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomainUserComponentApplication.class, args);
    }

}

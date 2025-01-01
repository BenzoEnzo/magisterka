package pl.benzo.enzo.magisterka.basecomponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.benzo.enzo.magisterka.basejava.annotation.BenzoEnzoMagisterkaService;

@SpringBootApplication
@BenzoEnzoMagisterkaService(name = "MyService", address = "127.0.0.1", port = 8080)
public class BaseComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseComponentApplication.class, args);
    }

}

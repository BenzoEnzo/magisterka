package pl.benzo.enzo.magisterka.basejava;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.benzo.enzo.magisterka.basejava.annotation.BenzoEnzoMagisterkaService;

@SpringBootApplication
@EnableScheduling
@BenzoEnzoMagisterkaService(name = "Base0Component", address = "127.0.0.1", port = 8081)
public class BaseJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseJavaApplication.class, args);
    }
}

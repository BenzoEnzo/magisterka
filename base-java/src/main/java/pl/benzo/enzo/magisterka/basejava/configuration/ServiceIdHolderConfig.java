package pl.benzo.enzo.magisterka.basejava.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.benzo.enzo.magisterka.basejava.model.ServiceIdHolder;

@Configuration
public class ServiceIdHolderConfig {
    @Bean
    public ServiceIdHolder serviceIdHolder(){
        return new ServiceIdHolder();
    }
}

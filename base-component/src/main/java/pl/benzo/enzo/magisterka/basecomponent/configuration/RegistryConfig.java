package pl.benzo.enzo.magisterka.basecomponent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.benzo.enzo.magisterka.basejava.model.ServiceIdHolder;
import pl.benzo.enzo.magisterka.basejava.service.RegistryService;

@Configuration
public class RegistryConfig {
    @Bean
    public RegistryService registryService() {
        return new RegistryService(new RestTemplate());
    }

    @Bean
    public ServiceIdHolder serviceIdHolder() {
        return new ServiceIdHolder();
    }
}

package pl.benzo.enzo.magisterka.basecomponent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.benzo.enzo.magisterka.basejava.model.ServiceIdHolder;
import pl.benzo.enzo.magisterka.basejava.processor.BenzoEnzoMagisterkaServiceProcessor;
import pl.benzo.enzo.magisterka.basejava.service.HeartBeatScheduler;
import pl.benzo.enzo.magisterka.basejava.service.OnInitService;
import pl.benzo.enzo.magisterka.basejava.service.RegistryService;

@Configuration
public class BenzoEnzoMagisterkaServiceConfig {
    @Bean
    public HeartBeatScheduler heartBeatScheduler(){
        return new HeartBeatScheduler(registryService(),serviceIdHolder());
    }
    @Bean
    public OnInitService onInitService(){
        return new OnInitService(registryService(),serviceIdHolder());
    }
    @Bean
    public BenzoEnzoMagisterkaServiceProcessor benzoEnzoMagisterkaServiceProcessor() {
        return new BenzoEnzoMagisterkaServiceProcessor(onInitService());
    }
    @Bean
    public RegistryService registryService() {
        return new RegistryService(new RestTemplate());
    }

    @Bean
    public ServiceIdHolder serviceIdHolder() {
        return new ServiceIdHolder();
    }
}

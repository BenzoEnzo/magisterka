package pl.benzo.enzo.magisterka.basejava.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.AnnotationUtils;
import pl.benzo.enzo.magisterka.basejava.annotation.BenzoEnzoMagisterkaService;
import pl.benzo.enzo.magisterka.basejava.service.OnInitService;

@Component
public class BenzoEnzoMagisterkaServiceProcessor implements BeanPostProcessor {

    private final OnInitService onInitService;

    public BenzoEnzoMagisterkaServiceProcessor(OnInitService onInitService) {
        this.onInitService = onInitService;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        BenzoEnzoMagisterkaService annotation = AnnotationUtils.findAnnotation(bean.getClass(), BenzoEnzoMagisterkaService.class);
        if (annotation != null) {
            onInitService.registerService(annotation.name(), annotation.address(), annotation.port());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

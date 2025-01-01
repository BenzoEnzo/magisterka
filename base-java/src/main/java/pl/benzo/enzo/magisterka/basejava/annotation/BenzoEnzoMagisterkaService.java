package pl.benzo.enzo.magisterka.basejava.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BenzoEnzoMagisterkaService {
    String name() default "";
    String address() default "127.0.0.1";
    int port() default 0; // 0 oznacza losowy port
}

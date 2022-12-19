package ar.lamansys.sgx.backoffice;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(BackofficeAutoConfiguration.class)
@Configuration
public @interface EnableBackoffice {
}
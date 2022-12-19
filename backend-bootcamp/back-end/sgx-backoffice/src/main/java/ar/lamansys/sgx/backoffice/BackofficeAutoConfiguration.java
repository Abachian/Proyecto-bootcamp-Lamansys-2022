package ar.lamansys.sgx.backoffice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "ar.lamansys.sgx.backoffice")
@ComponentScan(basePackages = {"ar.lamansys.sgx.backoffice"})
@EnableJpaRepositories(basePackages = {"ar.lamansys.sgx.backoffice"})
@PropertySource(value = "classpath:backoffice.properties", ignoreResourceNotFound = true)
public class BackofficeAutoConfiguration {
}
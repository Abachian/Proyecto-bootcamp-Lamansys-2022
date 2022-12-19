package ar.lamansys.app;


import ar.lamansys.cart.EnableCart;
import ar.lamansys.sgx.shared.EnableSharedLibrary;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableSharedLibrary
@EnableCart()
@ComponentScan(basePackages = {"ar.lamansys.app"})
@EnableJpaRepositories(basePackages = {"ar.lamansys.app"})
@EntityScan(basePackages = {"ar.lamansys.app"})
public class AppAutoConfiguration {
}
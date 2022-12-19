package ar.lamansys.app.security.infrastructure.configuration;

import ar.lamansys.sgx.shared.configuration.ActuatorConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final ActuatorConfiguration actuatorConfiguration;

	public WebSecurityConfiguration(ActuatorConfiguration actuatorConfiguration) {
		this.actuatorConfiguration = actuatorConfiguration;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		// @formatter:off
		httpSecurity.csrf().disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/users/activationlink/resend").permitAll()
		.antMatchers("/actuator/health").permitAll()
		.antMatchers("/actuator/info").permitAll()
		.antMatchers("/actuator/**").access(actuatorConfiguration.getAccessInfo())
		.antMatchers("/**").permitAll()
		.anyRequest().permitAll();

		// @formatter:on
		httpSecurity.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

	}
}


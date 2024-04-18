package br.com.ecommerce.pedidos.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.ecommerce.pedidos.api.security.JwtSecurity;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().addFilterAfter(new JwtSecurity(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.requestMatchers(HttpMethod.POST, "/api/login").permitAll()
		.requestMatchers(HttpMethod.POST, "/api/login/create").permitAll()
		.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.anyRequest()
		.authenticated();

return http.build();

	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(SWAGGER);
	}

	// configuração para liberar a documentação do SWAGGER
		private static final String[] SWAGGER = {
				"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
				"/configuration/security", "/swagger-ui.html", "/webjars/**",
				"/v3/api-docs/**", "/swagger-ui/**", "swagger-ui/index.html", "/actuator/health**","/actuator/prometheus**"
		};
	
}
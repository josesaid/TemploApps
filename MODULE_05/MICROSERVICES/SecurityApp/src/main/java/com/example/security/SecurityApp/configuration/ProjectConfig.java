package com.example.security.SecurityApp.configuration;

import com.example.security.SecurityApp.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author josesaidolanogarcia
 */
@Configuration
@EnableWebSecurity
public class ProjectConfig {

	private final CustomUserDetailsService userDetailsService;

	public ProjectConfig(CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests(a->a.requestMatchers("/admin").hasRole("ADMIN"))
		.authorizeHttpRequests(a->a.requestMatchers("/user").hasRole("USER"))
		.authorizeHttpRequests(a->a.requestMatchers("/customer").hasRole("CUSTOMER")
		.anyRequest().authenticated()).formLogin(Customizer.withDefaults())
		.logout(l->l.permitAll());
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return builder.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		//return new Argon2PasswordEncoder(16, 32, 1, 4096, 100);
	}
	
	
}

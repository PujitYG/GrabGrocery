package com.microservice.authservice.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	ApplicationUserDetailsService userDetailsService;
	
	@Autowired
	JWTOncePerRequestFilter filter;
	
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    authProvider.setHideUserNotFoundExceptions(false);
	    return authProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
		return http.csrf().disable()
//		.antMatcher("/public/**")
		.authorizeRequests()
		.anyRequest()
		.permitAll()
//		.authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
//		.anonymous().disable()
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
		.build();
		
		
		
	}
	
//	@Bean
//	public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
//		return http.csrf().disable()
//		.antMatcher("/private/**")
//		.authorizeRequests()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.build();
//		
//	}
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	

}

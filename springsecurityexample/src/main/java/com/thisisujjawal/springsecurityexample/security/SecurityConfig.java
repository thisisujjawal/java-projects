package com.thisisujjawal.springsecurityexample.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.thisisujjawal.springsecurityexample.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	/**
	 * UserDetailsService is the service in which all the user, type of user,
	 * roles, authorities etc, username, password etc are defined.
	 * 
	 * No DB : use InMemorityUserDetailsManager
	 * DB : Define your own UserDetailsService by implementing UserDetailsService(loaduserbyusername) interface
	 * 
	 * @param encoder
	 * @return
	 */
	@Bean
	//authentication
	public UserDetailsService userDetailsService() {
		//-----------------------------------------------------------
		//No DB : InMemoryUserDetailsManager(userDetailsService(PasswordEncoder encoder))
		
//		UserDetails admin = User.withUsername("admin")
//				.password(encoder.encode("admin"))
//				.roles("ADMIN")
//				.build();
//		UserDetails user = User.withUsername("user")
//				.password(encoder.encode("user"))
//				.roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(admin, user);
		
		
		//-----------------------------------------------------------
		
		//DB
		return new MyUserDetailsService();
	}
	
	@Bean
	//authorization
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/hello", "/user/**").permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/**")
				.authenticated().and().formLogin()
				.and().build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * This method is used to authenticate the user...
	 * As in case of inMemoryUserDetailsManager... the authentication is already
	 * provided(No need for this method) but in this case we have defined our own userDetailsService
	 * 
	 * So now spring security needs to authenticate the user so it needs 2 things from you:
	 * 1. Which one is the UserDetailsService
	 * 2. What passwordEncoder you are using
	 * 
	 * After giving it.. authenticationProvider will get UserDetails object by UserDetailsService and 
	 * then authenticate and create a authentication Object
	 * @return
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
		
	}

}

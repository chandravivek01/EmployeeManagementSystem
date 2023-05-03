package com.g2b1.ems.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.g2b1.ems.service.UserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {

		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(bCryptPasswordEncoder());
		return auth;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/employee/addemployee", "/employee/save", "/employee/update", "/employee/delete").hasAuthority("ADMIN")
		.antMatchers("/employee").hasAnyAuthority("USER", "ADMIN")
		.antMatchers("/","/registration", "/registration/addrole", "/registration/save", "/employee/view", "/employee/search").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginProcessingUrl("/login").successForwardUrl("/employee").permitAll()
		.and()
		.logout().logoutSuccessUrl("/registration").permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/403")
		.and()
		.cors().and().csrf().disable();

		
//		CONFIGURATION FOR TICKET-CONTROLLER APPLICATION
		
//		http.authorizeRequests()
//		.antMatchers("/admin/tickets/save", "/admin/tickets/newTicket","/admin/tickets","/admin/tickets/update", "/admin/tickets/view").hasAnyAuthority("USER", "ADMIN")
//		.antMatchers("/admin/tickets/edit", "/admin/tickets/delete").hasAuthority("ADMIN")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().loginProcessingUrl("/login").successForwardUrl("/admin/tickets").permitAll()
//		.and()
//		.logout().logoutSuccessUrl("/login").permitAll()
//		.and()
//		.exceptionHandling().accessDeniedPage("/admin/tickets/403")
//		.and()
//		.cors().and().csrf().disable();

		
		
		
//		CONFIGURATION FOR CUSTOM LOGIN BEFORE ADDING AUTHORIZATIONS FOR "ADMIN" AND "USER" (WITHOUT USER AND ADMIN AUTHORIZATION)
		
//		http.authorizeRequests()
//		.antMatchers("/employee", "/registration/addrole","/registration", "/registration/save").permitAll()
//		.antMatchers("/employee/addemployee", "/employee/save", "/employee/update", "/employee/delete").hasAuthority("ADMIN")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().loginPage("/login").permitAll()
//		.and()
//		.logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/login?logout")
//		.permitAll()
//		.and()
//		.exceptionHandling().accessDeniedPage("/403");
	}

}

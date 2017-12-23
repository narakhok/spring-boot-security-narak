package com.spring.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private AuthenticationSuccessHandler successHandler;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("pwd").roles("USER").and()
			.withUser("dba").password("pwd").roles("DBA").and()
			.withUser("admin").password("pwd").roles("ADMIN").and()		
			.withUser("narak").password("pwd").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//enable form login
		
		http.formLogin()
		.loginPage("/login")
		.successHandler(successHandler)
		
		//logout		
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))	
			.logoutSuccessUrl("/");
		http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN").antMatchers("/dba/**")
				.hasAnyRole("ADMIN", "DBA").antMatchers("/user/**").hasAnyRole("ADMIN", "DBA", "USER");
	}

}

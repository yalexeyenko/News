package com.epam.yalexeyenko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UrlAuthenticationSuccessHandler urlAuthenticationSuccessHandler;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth .inMemoryAuthentication().withUser("user").password("user").roles("USER");
		auth .inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()				
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/cabinet/**").access("hasRole('USER')")
				.antMatchers("/home/**").access("hasRole('ANONYMOUS')")
				.and()
			.formLogin()
				.loginPage("/login")
				.successHandler(urlAuthenticationSuccessHandler)
				.usernameParameter("username")
				.passwordParameter("password")
				.failureUrl("/login?error=true")
				.and()
			.logout()
				.logoutUrl("/logout")
//				.logoutSuccessHandler(logoutSuccessHandler)
				.invalidateHttpSession(true)
//				.addLogoutHandler(logoutHandler)
//				.deleteCookies(cookieNamesToClear)
				.and()
			.csrf()
				.and()
			.exceptionHandling().accessDeniedPage("/access_denied")
				.and()
			.logout()
				.permitAll();
	}
}

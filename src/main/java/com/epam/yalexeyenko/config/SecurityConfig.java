package com.epam.yalexeyenko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UrlAuthenticationSuccessHandler urlAuthenticationSuccessHandler;
	@Autowired
	AuthentificationProvider authentificationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()	
				.antMatchers("/admin").access("hasRole('ADMIN')")
				.antMatchers("/cabinet").access("hasRole('USER')")
				.antMatchers("/home").access("hasRole('ANONYMOUS')")
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
				.logoutSuccessUrl("/home")
//				.logoutSuccessHandler(logoutSuccessHandler)
				.invalidateHttpSession(true)
//				.addLogoutHandler(logoutHandler)
//				.deleteCookies(cookieNamesToClear)
				.and()
			.csrf()
				.and()
			.exceptionHandling().accessDeniedPage("/403")
				.and()
			.logout()
				.permitAll();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(authentificationProvider);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
}

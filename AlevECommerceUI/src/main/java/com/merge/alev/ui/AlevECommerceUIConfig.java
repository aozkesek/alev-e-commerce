package com.merge.alev.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AlevECommerceUIConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        	.antMatchers("/administration", "/administration/**").fullyAuthenticated()
        	.antMatchers("/**").permitAll()
            .and()
        .formLogin()
            .loginPage("/adminlogin")
            .defaultSuccessUrl("/administration")
            .permitAll()
            .and()
        .logout()
        	.clearAuthentication(true)
        	.deleteCookies("JSESSIONID")
        	.logoutUrl("/adminlogout")
            .permitAll()
            .and()
        .sessionManagement()
        	.invalidSessionUrl("/sessionexpired");
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth
			.inMemoryAuthentication()
				.withUser("admin").password("adminPwd").roles("ADMIN");
		
		
	}
	
	

}

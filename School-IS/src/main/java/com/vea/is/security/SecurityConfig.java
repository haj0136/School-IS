package com.vea.is.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	/*
    @Autowired
    public void init(AuthenticationManagerBuilder builder) throws Exception {
        PasswordEncoder encoder = getPasswordEncoder();
        builder.inMemoryAuthentication().withUser("marek").password(encoder.encode("test")).roles("ADMIN").and()
                .withUser("user").password(encoder.encode("test")).roles("USER");
    }
	*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/public/**", "/css/**", "/rest/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .and().logout().permitAll().invalidateHttpSession(true)
            .logoutSuccessUrl("/login?logout").and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {return PasswordEncoderFactories.createDelegatingPasswordEncoder();}
}

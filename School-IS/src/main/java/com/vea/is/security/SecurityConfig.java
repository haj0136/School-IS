package com.vea.is.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void init(AuthenticationManagerBuilder builder) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        builder.inMemoryAuthentication().withUser("marek").password(encoder.encode("test")).roles("ADMIN").and()
                .withUser("user").password(encoder.encode("test")).roles("USER");
        builder.authenticationProvider(new AuthenticationProvider() {

            @Override
            public boolean supports(Class<?> authentication) {
                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String name = authentication.getName();
                String password = authentication.getCredentials() != null ? authentication.getCredentials().toString()
                        : null;
                if (name.startsWith("a") && password != null) {
                    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    return new UsernamePasswordAuthenticationToken(name, password, authorities);
                }
                throw new AuthenticationServiceException("Jmeno musi zacinat na a");
            }
        });
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
}

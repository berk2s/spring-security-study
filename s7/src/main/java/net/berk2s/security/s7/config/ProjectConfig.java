package net.berk2s.security.s7.config;

import lombok.RequiredArgsConstructor;
import net.berk2s.security.s7.services.AuthenticationProviderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderService authenticationProviderService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProviderService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .defaultSuccessUrl("/hello", true);

        http.authorizeRequests()
                .anyRequest().authenticated();
    }
}

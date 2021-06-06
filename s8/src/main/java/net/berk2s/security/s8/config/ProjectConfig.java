package net.berk2s.security.s8.config;

import lombok.RequiredArgsConstructor;
import net.berk2s.security.s8.services.AuthenticationProviderService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderService authenticationProviderService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProviderService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .httpBasic()
                    .and()
                .authorizeRequests()
                    .mvcMatchers("/read").hasAuthority("read")
                    .mvcMatchers("/write").hasAuthority("write")
                    .mvcMatchers("/update").hasAuthority("update")
                    .mvcMatchers("/delete").hasAuthority("delete")
                    .mvcMatchers("/manager").access("hasAuthority('ROLE_MANAGER')")
                    .mvcMatchers("/admin").access("hasAuthority('ROLE_MANAGER') || hasAuthority('ROLE_ADMIN')")
                    .mvcMatchers("/manager2").access("hasRole('MANAGER')")
                    .mvcMatchers("/admin2").access("hasRole('ADMIN') || hasRole('MANAGER')")
                .anyRequest().permitAll();
    }
}

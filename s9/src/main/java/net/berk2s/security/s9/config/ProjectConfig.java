package net.berk2s.security.s9.config;

import lombok.RequiredArgsConstructor;
import net.berk2s.security.s9.security.AuthenticationLoggingFilter;
import net.berk2s.security.s9.security.RequestValidationFilter;
import net.berk2s.security.s9.security.StaticKeyAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new RequestValidationFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthenticationLoggingFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterAt(staticKeyAuthenticationFilter,
                        BasicAuthenticationFilter.class)
                .authorizeRequests()
                    .anyRequest().permitAll();
    }
}

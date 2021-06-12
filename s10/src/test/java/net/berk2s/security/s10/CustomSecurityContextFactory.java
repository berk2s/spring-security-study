package net.berk2s.security.s10;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class CustomSecurityContextFactory implements WithSecurityContextFactory<WithCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithCustomUser withCustomUser) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        var authentication = new UsernamePasswordAuthenticationToken(withCustomUser.username(), null, null);

        securityContext.setAuthentication(authentication);

        return securityContext;
    }
}

package net.berk2s.security.s7.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.berk2s.security.s7.security.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationProviderService implements AuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        SecurityUser securityUser = userDetailsService.loadUserByUsername(username);

        switch (securityUser.getUser().getEncryptionAlgorithm()) {
            case BCRYPT:
                return checkPassword(securityUser, password, bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(securityUser, password, sCryptPasswordEncoder);
        }

        log.warn("Invalid credentials [{}]", username);
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

    private Authentication checkPassword(SecurityUser securityUser, String rawPass, PasswordEncoder passwordEncoder) {
        if(passwordEncoder.matches(rawPass, securityUser.getPassword())) {
            return new UsernamePasswordAuthenticationToken(securityUser.getUsername(),
                    securityUser.getPassword(),
                    securityUser.getAuthorities());
        } else {
            log.warn("Invalid password [{}]", securityUser.getUsername());
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}

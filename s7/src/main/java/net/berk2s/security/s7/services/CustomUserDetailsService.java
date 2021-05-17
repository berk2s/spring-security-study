package net.berk2s.security.s7.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.berk2s.security.s7.domain.User;
import net.berk2s.security.s7.repository.UserRepository;
import net.berk2s.security.s7.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public SecurityUser loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(s);

        if(optionalUser.isEmpty()) {
            log.warn("Invalid username [{}]", s);
            throw new UsernameNotFoundException("Invalid username");
        }

        return new SecurityUser(optionalUser.get());
    }
}

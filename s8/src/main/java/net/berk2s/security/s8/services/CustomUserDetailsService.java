package net.berk2s.security.s8.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.berk2s.security.s8.domain.User;
import net.berk2s.security.s8.repository.UserRepository;
import net.berk2s.security.s8.security.SecurityUser;
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
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(s);

        if (optionalUser.isEmpty()) {
            log.warn("Cannot find user by username [{}]", s);
            throw new UsernameNotFoundException("Username not found");
        }

        return new SecurityUser(optionalUser.get());
    }
}

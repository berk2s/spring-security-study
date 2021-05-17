package net.berk2s.security.s4.services;

import net.berk2s.security.s4.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import net.berk2s.security.s4.repository.UserRepository;
import net.berk2s.security.s4.security.SecurityUser;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if(optionalUser.isEmpty()) {
            log.error("Username not found [username: {}]", username);
            throw new UsernameNotFoundException("Username not found!");
        }

        return new SecurityUser(optionalUser.get());
    }
}

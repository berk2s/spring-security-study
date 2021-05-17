package net.berk2s.security.s4.bootstrap;

import net.berk2s.security.s4.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import net.berk2s.security.s4.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("john");
        user.setPassword(passwordEncoder.encode("12345"));
        System.out.println();
        user.setAuthorities("read");

        userRepository.save(user);
    }
}

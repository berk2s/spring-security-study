package net.berk2s.security.s8.bootstrap;

import lombok.RequiredArgsConstructor;
import net.berk2s.security.s8.domain.Authority;
import net.berk2s.security.s8.domain.User;
import net.berk2s.security.s8.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("12345"));
        user1.addAuthority(Authority.builder().name("read").build());
        user1.addAuthority(Authority.builder().name("write").build());
        user1.addAuthority(Authority.builder().name("update").build());
        user1.addAuthority(Authority.builder().name("ROLE_ADMIN").build());
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("12345"));
        user2.addAuthority(Authority.builder().name("read").build());
        user2.addAuthority(Authority.builder().name("write").build());
        user2.addAuthority(Authority.builder().name("update").build());
        user2.addAuthority(Authority.builder().name("delete").build());
        user2.addAuthority(Authority.builder().name("ROLE_MANAGER").build());
        user2.setAccountNonExpired(true);
        user2.setAccountNonLocked(true);
        user2.setCredentialsNonExpired(true);
        user2.setEnabled(true);

        userRepository.saveAll(List.of(user1, user2));
    }
}

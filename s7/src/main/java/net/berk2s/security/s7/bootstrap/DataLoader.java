package net.berk2s.security.s7.bootstrap;

import lombok.RequiredArgsConstructor;
import net.berk2s.security.s7.domain.Authority;
import net.berk2s.security.s7.domain.EncryptionAlgorithm;
import net.berk2s.security.s7.domain.User;
import net.berk2s.security.s7.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Authority authority = new Authority();
        authority.setName("read");

        User user = new User();
        user.setUsername("john");
        user.setPassword(bCryptPasswordEncoder.encode("12345"));
        user.setEncryptionAlgorithm(EncryptionAlgorithm.BCRYPT);
        user.addAuthority(authority);

        userRepository.save(user);
    }
}

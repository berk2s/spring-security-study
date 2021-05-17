package net.berk2s.security.s7.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm encryptionAlgorithm;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthority(Authority authority) {
        authorities.add(authority);
        authority.setUser(this);
    }

}

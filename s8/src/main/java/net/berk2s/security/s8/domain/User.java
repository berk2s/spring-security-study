package net.berk2s.security.s8.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    private List<Authority> authorities = new ArrayList<>();

    @Column
    private boolean isAccountNonLocked;

    @Column
    private boolean isAccountNonExpired;

    @Column
    private boolean isCredentialsNonExpired;

    @Column
    private boolean enabled;

    public void addAuthority(Authority authority) {
        authorities.add(authority);
        authority.setUser(this);
    }

}

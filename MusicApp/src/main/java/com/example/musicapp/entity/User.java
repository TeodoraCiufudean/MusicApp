package com.example.musicapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Table (name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column (name = "userId")
    private int userId;
    @Column (name = "firstName")
    private String firstName;
    @Column (name = "lastName")
    private String lastName;
    @Column (name = "emailAddress")
    private String emailAddress;
    @Column (name = "password")
    private String password;
    @Column (name = "age")
    private int age;

    //@ElementCollection (fetch = FetchType.EAGER)
    @Column (name = "roles")
    @Enumerated (EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

package com.showmeyourcode.projects.springmvc.application;

import com.showmeyourcode.projects.springmvc.infrastructure.entity.AppUserEntity;
import com.showmeyourcode.projects.springmvc.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final String BCRYPT = "{bcrypt}";
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public boolean isLoginAlreadyExists(String login) {
        return userRepository.getByLogin(login) == null;
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        AppUserEntity user = userRepository.getByLogin(name);
        if (user == null) {
            log.warn("A user '{}' is not found.", name);
            return new User(
                    name,
                    "",
                    false,
                    true,
                    true,
                    true,
                    Collections.emptyList()
            );
        }

        return new User(
                user.getNick(),
                BCRYPT + user.getPassword(),
                true,
                true,
                true,
                true,
                getGrantedAuthorities(user)
        );
    }

    public void register(AppUserEntity entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
    }

    public Long userId(String name) {
        return userRepository.getByLogin(name).getId();
    }

    private List<GrantedAuthority> getGrantedAuthorities(AppUserEntity user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }
}

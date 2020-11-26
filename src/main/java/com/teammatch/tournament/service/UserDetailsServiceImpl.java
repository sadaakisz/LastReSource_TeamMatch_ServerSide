package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.service.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements DefaultUserDetailsService {
    private static final String DEFAULT_USERNAME = "nick.reeves@gmail.com";
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> getAll() {
        return Arrays.asList(
                new User("nick.reeves@gmail.com",
                        passwordEncoder.encode("password"),
                        DEFAULT_AUTHORITIES),
                new User("lily.pichu@hotmail.com",
                        passwordEncoder.encode("password"),
                        DEFAULT_AUTHORITIES),
                new User("frank.wild@natgeo.com",
                        passwordEncoder.encode("password"),
                        DEFAULT_AUTHORITIES)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String defaultPassword = passwordEncoder.encode("password");
        if(DEFAULT_USERNAME.equals(username)) {
            return new User(DEFAULT_USERNAME, defaultPassword, DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException(String.format("User not found with username %s", username));

    }
}

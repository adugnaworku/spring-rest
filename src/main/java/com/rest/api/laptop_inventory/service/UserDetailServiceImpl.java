package com.rest.api.laptop_inventory.service;


import com.rest.api.laptop_inventory.model.User;
import com.rest.api.laptop_inventory.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserName(username);

        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Users Name not found"));

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), user.isEnabled(),
                true,
                true,
                true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return  singletonList(new SimpleGrantedAuthority(role));
    }
}

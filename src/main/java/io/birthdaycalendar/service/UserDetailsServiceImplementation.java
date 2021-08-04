package io.birthdaycalendar.service;

import io.birthdaycalendar.models.User;
import io.birthdaycalendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImplementation(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                    true, true, true, true, getAuthorities("ROLE_USER"));
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
    	ArrayList<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    	list.add(new SimpleGrantedAuthority(role));
        return list; 
    }
}

package ru.danil.NauJava.service.userService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.danil.NauJava.Entities.User.User;
import ru.danil.NauJava.Entities.User.UserRole;
import ru.danil.NauJava.Repository.userRepository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        user.setUserRole(Collections.singleton(UserRole.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myAppUser = userRepository.findByUsername(username);
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(myAppUser.getUsername(),
                myAppUser.getPassword(), mapRoles(myAppUser.getUserRole()));
        return user;
    }

    private Collection<GrantedAuthority> mapRoles(Set<UserRole> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).
                collect(Collectors.toList());
    }
}

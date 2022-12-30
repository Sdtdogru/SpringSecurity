package com.example.springsecurity.services;

import com.example.springsecurity.models.User;
import com.example.springsecurity.models.UserRole;
import com.example.springsecurity.repos.UserRepository;
import com.example.springsecurity.repos.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers()
    {
        log.info("Get All Users...");
        return userRepository.findAll();
    }
    public User saveUser(User user)
    {
        log.info("Saving user...");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
    public User getUser(String username)
    {
        return userRepository.findByUsername(username);
    }
    public UserRole saveRole(UserRole role)
    {
        log.info("Saving role...");
        userRoleRepository.save(role);
        return role;
    }

    public void  addRoleToUser(String username, String roleName)
    {
        log.info("Adding {} role to user {}...", roleName,username);
        User user = getUser(username);
        UserRole role = userRoleRepository.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Username not found!");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user == null)
            throw new UsernameNotFoundException("Username not found!");
        return user.get();
    }
}

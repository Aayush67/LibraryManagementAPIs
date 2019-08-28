package com.aayush.libraryManagementAPIs.service;

import com.aayush.libraryManagementAPIs.model.entity.Role;
import com.aayush.libraryManagementAPIs.model.entity.UserRegistration;
import com.aayush.libraryManagementAPIs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserRegistration> user = userRepository.findByEmail(email);

/*        Matches the encoded password with raw password
        boolean match =  passwordEncoder.matches("aayush",user.get().getPassword());*/


//        if (user == null) throw new UsernameNotFoundException(email);

        if(user.isPresent()) {

            UserRegistration credentials = user.get();
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role : credentials.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
            }

            return new org.springframework.security.core.userdetails.User(credentials.getEmail(), credentials.getPassword(), grantedAuthorities);
        }
        else
            throw new UsernameNotFoundException("Invalid Credentials");
    }

}

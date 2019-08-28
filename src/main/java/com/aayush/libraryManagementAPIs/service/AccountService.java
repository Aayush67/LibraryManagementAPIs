package com.aayush.libraryManagementAPIs.service;

import com.aayush.libraryManagementAPIs.common.Exception.LibraryException;
import com.aayush.libraryManagementAPIs.common.messages.LibraryMessages;
import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import com.aayush.libraryManagementAPIs.model.entity.Role;
import com.aayush.libraryManagementAPIs.model.entity.UserRegistration;
import com.aayush.libraryManagementAPIs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailService emailService;

//    @Autowired
//    private  UserService userService;



    public CommonResponse registerUser(UserRegistration user) throws LibraryException
    {
        Optional<UserRegistration> optional = accountRepository.findByEmail(user.getEmail());
        optional.ifPresent(res->{
                throw  new LibraryException(LibraryMessages.USER_ALREADY_EXISTS_CODE, LibraryMessages.USER_ALREADY_EXISTS_MESSAGE);
        });
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode("aayush"));
        Role newRole = new Role();
//        newRole.setRoleId(1);
        newRole.setRole("USER");
        Set<Role> setRole = new HashSet<Role>();
        setRole.add(newRole);
        user.setRoles(setRole);
        accountRepository.save(user);
//        emailService.sendEmail(user);
//        userService.save(user);
        return new CommonResponse(LibraryMessages.SUCCESS_CODE,LibraryMessages.SUCCESS_MESSAGE);
    }

    public Optional<UserRegistration> findByEmail(String email)
    {
        return accountRepository.findByEmail(email);
    }

    public void addAdminAccount(UserRegistration admin)
    {
        accountRepository.save(admin);
    }
}

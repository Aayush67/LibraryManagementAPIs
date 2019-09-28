package com.aayush.libraryManagementAPIs.service;

import com.aayush.libraryManagementAPIs.common.Exception.LibraryException;
import com.aayush.libraryManagementAPIs.common.messages.LibraryMessages;
import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import com.aayush.libraryManagementAPIs.model.entity.*;
import com.aayush.libraryManagementAPIs.model.enums.Status;
import com.aayush.libraryManagementAPIs.model.response.BookResponse;
import com.aayush.libraryManagementAPIs.model.response.GenericResponse;
import com.aayush.libraryManagementAPIs.model.response.UserListResponse;
import com.aayush.libraryManagementAPIs.model.response.UserResponse;
import com.aayush.libraryManagementAPIs.repository.AccountRepository;
import com.aayush.libraryManagementAPIs.repository.BookRepository;
import com.aayush.libraryManagementAPIs.repository.UserBookMappingRepository;
import com.aayush.libraryManagementAPIs.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private UserBookMappingRepository userBookMappingRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BookRepository bookRepository;

//    @Autowired
//    private  UserService userService;


    /**
     * Register and Update User
     * @param user
     * @param email
     * @return
     * @throws LibraryException
     */
    public CommonResponse registerUser(UserResponse user, String email) throws LibraryException
    {
        if(Objects.isNull(user.getUserId())) {
            Optional<UserRegistration> optional = accountRepository.findByEmail(email);
            optional.ifPresent(res -> {
                throw new LibraryException(LibraryMessages.USER_ALREADY_EXISTS_CODE, LibraryMessages.USER_ALREADY_EXISTS_MESSAGE);
            });
        }
        UserRegistration userInfo = new UserRegistration();
        if(!Objects.isNull(user.getUserId())){
            userInfo.setId(user.getUserId());
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userInfo.setPassword(passwordEncoder.encode("aayush"));
        userInfo.setEmail(email);
        Role newRole = new Role();
//        newRole.setRoleId(1);
        newRole.setRole("USER");
        Set<Role> setRole = new HashSet<Role>();
        setRole.add(newRole);
        userInfo.setRoles(setRole);

        UserDetail userDetail = new UserDetail();
        if(!Objects.isNull(user.getUserInfoId())){
            userDetail.setUserId(user.getUserInfoId());
        }
        userDetail.setUsername(user.getName());
        userDetail.setAddress(user.getAddress());
        userDetail.setAge(user.getAge());
        userDetail.setPhone(user.getPhone());
        userInfo.setUserDetail(userDetail);

        accountRepository.save(userInfo);
//        emailService.sendEmail(user);
//        userService.save(user);
        return new CommonResponse(LibraryMessages.SUCCESS_CODE,LibraryMessages.SUCCESS_MESSAGE);
    }

    public CommonResponse deleteUser(Long userId) throws LibraryException
    {
//        Optional<UserRegistration> userOptional =  accountRepository.findById(userId);
//        UserRegistration userData = userOptional.get();
       accountRepository.deleteById(userId);
//       userDetailRepository.deleteById(userData.getUserDetail().getUserId());

        List<UserBookMapping> userBookMappings = userBookMappingRepository.getUserIdAndBookId(userId);
        if(userBookMappings.size() > 0){
            List<Long> bookIds = new ArrayList<>();
            for(UserBookMapping ubm: userBookMappings) {
                bookIds.add(ubm.getBookId());
            }
            Status status = Status.AVAILABLE;
            bookRepository.updateMultipleBookStatus(bookIds,status , null, null);
            userBookMappingRepository.deleteUserById(userId);
        }

//       userBookMappingRepository.deleteUserById(userId);


//        accountRepository.save(userInfo);
//        emailService.sendEmail(user);
//        userService.save(user);
        return new CommonResponse(LibraryMessages.SUCCESS_CODE,LibraryMessages.SUCCESS_MESSAGE);
    }



    public UserListResponse getAllUsers(){
       List<UserRegistration> userListData = accountRepository.findAll();
       ArrayList<UserResponse> userResponseList = new ArrayList<>();
        userListData.forEach( user -> {
           UserResponse userData = new UserResponse();
           userData.setUserId(user.getId());
           userData.setEmail(user.getEmail());
           userData.setName(user.getUserDetail().getUsername());
           userData.setAddress(user.getUserDetail().getAddress());
           userData.setPhone(user.getUserDetail().getPhone());
           userData.setAge(user.getUserDetail().getAge());
            user.getRoles().forEach( role -> {
                userData.setRole(role.getRole());
            });
//           userData.setRole(user.getRoles().toString());
            if(userData.getRole().equals("USER")) {
                userResponseList.add(userData);
            }

       });
        UserListResponse userList = new UserListResponse();
        userList.setResponseMessage(LibraryMessages.SUCCESS_MESSAGE);
        userList.setResponseCode(LibraryMessages.SUCCESS_CODE);
        userList.setResponseData(userResponseList);
        return userList;

    }

    public GenericResponse<UserResponse> getUserById(Long userId){
        Optional<UserRegistration> userOptional =  accountRepository.findById(userId);
        UserRegistration userData = userOptional.get();
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userData.getId());
        userResponse.setUserInfoId(userData.getUserDetail().getUserId());
        userResponse.setEmail(userData.getEmail());
        userResponse.setName(userData.getUserDetail().getUsername());
        userResponse.setAddress(userData.getUserDetail().getAddress());
        userResponse.setPhone(userData.getUserDetail().getPhone());
        userResponse.setAge(userData.getUserDetail().getAge());
        userData.getRoles().forEach( role -> {
            userResponse.setRole(role.getRole());
        });
        GenericResponse<UserResponse> returnData = new GenericResponse();
        returnData.setResponseMessage(LibraryMessages.SUCCESS_MESSAGE);
        returnData.setResponseCode(LibraryMessages.SUCCESS_CODE);
        returnData.setResponseData(userResponse);
        return returnData;
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

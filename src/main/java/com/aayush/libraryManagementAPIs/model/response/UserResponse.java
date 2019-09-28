package com.aayush.libraryManagementAPIs.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {
    private Long userId; //registered user id
    private Long userInfoId; //userdetail id
    private String email;
    private String name;
    private String role;
    private String phone;
    private String address;
    private String age;
}

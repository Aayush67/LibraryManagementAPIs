package com.aayush.libraryManagementAPIs.common.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class AuthSuccess {
    private String email;
    private String username;
    private Long userId;
    private List<String> role;
}

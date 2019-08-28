package com.aayush.libraryManagementAPIs.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthFail {
    private int code;
    private String message;

    public AuthFail(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

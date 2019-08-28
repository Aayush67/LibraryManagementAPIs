package com.aayush.libraryManagementAPIs.common.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LibraryException extends RuntimeException {
//    private static final long serialVersionUID = 1L;
    private Integer errorCode;
    private String errorMessage;

    public LibraryException(Integer errorCode, String errorMessage)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

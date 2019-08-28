package com.aayush.libraryManagementAPIs.common.Exception;

import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class LibraryExceptionHandler  {

    @ExceptionHandler(LibraryException.class)
    public CommonResponse handlePPLException(HttpServletRequest request, LibraryException ex) {
        CommonResponse response= new CommonResponse(ex.getErrorCode(), ex.getErrorMessage());
        return response;
    }
}

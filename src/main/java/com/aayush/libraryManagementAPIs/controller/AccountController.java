package com.aayush.libraryManagementAPIs.controller;

import com.aayush.libraryManagementAPIs.common.Exception.LibraryException;
import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import com.aayush.libraryManagementAPIs.model.entity.UserRegistration;
import com.aayush.libraryManagementAPIs.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/account")
@Api(tags = "Account Controller", description = "Register Students and Teachers")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins =  {"http://localhost:8585", "http://localhost:8787"},
//@CrossOrigin==>Allows all to hit
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Register User")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "Success"),
            @ApiResponse(code = 1003 , message = "User Already Exists")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResponse registration(@RequestBody UserRegistration user) throws LibraryException
    {
          return accountService.registerUser(user);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Principal principal) throws Exception
    {
        System.out.println("SSSS");
        return "Login";
    }

//    @RequestMapping(value = "/")
//    public String home() throws Exception
//    {
//       return "home";
//    }
}

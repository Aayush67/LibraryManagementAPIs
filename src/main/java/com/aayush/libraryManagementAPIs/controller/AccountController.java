package com.aayush.libraryManagementAPIs.controller;

import com.aayush.libraryManagementAPIs.common.Exception.LibraryException;
import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import com.aayush.libraryManagementAPIs.model.entity.UserDetail;
import com.aayush.libraryManagementAPIs.model.entity.UserRegistration;
import com.aayush.libraryManagementAPIs.model.response.BookResponse;
import com.aayush.libraryManagementAPIs.model.response.GenericResponse;
import com.aayush.libraryManagementAPIs.model.response.UserListResponse;
import com.aayush.libraryManagementAPIs.model.response.UserResponse;
import com.aayush.libraryManagementAPIs.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/register/{email}", method = RequestMethod.POST)
    public CommonResponse registration(@PathVariable("email") String email, @RequestBody UserResponse user) throws LibraryException
    {
          return accountService.registerUser(user,email);
    }

    @ApiOperation(value = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "Success"),
            @ApiResponse(code = 1003 , message = "User Already Exists")
    })
    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE)
    public CommonResponse updateUser(@PathVariable("userId") Long userId) throws LibraryException
    {
        return accountService.deleteUser(userId);
    }

    @ApiOperation(value = "Get All Users")
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public UserListResponse getAllUsers() throws LibraryException
    {
        return accountService.getAllUsers();
    }

    @ApiOperation(value = "Get User By Id")
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public GenericResponse<UserResponse> getUserById(@RequestParam("userId") Long userId) throws LibraryException
    {
        return accountService.getUserById(userId);
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

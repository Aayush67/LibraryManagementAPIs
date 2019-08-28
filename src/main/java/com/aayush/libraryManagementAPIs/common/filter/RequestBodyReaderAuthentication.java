package com.aayush.libraryManagementAPIs.common.filter;

import com.aayush.libraryManagementAPIs.common.messages.LibraryMessages;
import com.aayush.libraryManagementAPIs.common.model.AuthSuccess;
import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import com.aayush.libraryManagementAPIs.model.entity.UserRegistration;
import com.aayush.libraryManagementAPIs.model.request.LoginRequest;
import com.aayush.libraryManagementAPIs.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.aayush.libraryManagementAPIs.common.model.Response;

public class RequestBodyReaderAuthentication extends UsernamePasswordAuthenticationFilter {

    private static final Log LOG = LogFactory.getLog(RequestBodyReaderAuthentication.class);

    private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";

    @Autowired
    private AccountRepository userRepository;


//    public RequestBodyReaderAuthentication() {
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest authRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword(), new ArrayList<>());

            // Allow subclasses to set the "details" property
            setDetails(request, token);
            return this.getAuthenticationManager().authenticate(token);
        } catch(IOException e) {
            LOG.error(ERROR_MESSAGE, e);
            throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        res.setHeader("Access-Control-Allow-Origin", "*");
        final String userEmail = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
        final UserRegistration user = userRepository.findByEmail(userEmail).get();
        AuthSuccess loginSuccess = new AuthSuccess();
        loginSuccess.setEmail(user.getEmail());
        loginSuccess.setUsername(user.getUserDetail().getUsername());
        loginSuccess.setUserId(user.getUserDetail().getUserId());


        Map<String,Object> loginData = new HashMap<String, Object>();
        loginData.put("credentials",loginSuccess);
//        authSuccesses.add(loginSuccess);
        Response respone = new Response() ;
        respone.setSuccessResponse();
        respone.setResponse(loginData);
//        respone.setResponse(new HashMap<>().);
//        CommonResponse respone = new CommonResponse(1000,"success" , authSuccesses) ;


//        final String token = tokenProvider.getNewAuthToken(user);
//        final AuthResponse authResponse = new AuthResponse();
//        authResponse.setUser(user);
//        authResponse.setToken(token);

        final ObjectMapper objectMapper = new ObjectMapper();
        res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(objectMapper.writeValueAsString(respone));
        res.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
        final ObjectMapper objectMapper = new ObjectMapper();
        res.setStatus(404);
        res.setHeader("Access-Control-Allow-Origin", "*");
//        final AuthFail authFail= new AuthFail(1000, "Invalid Login");
        CommonResponse invalidLogin = new CommonResponse(LibraryMessages.INVALID_LOGIN__CODE, LibraryMessages.INVALID_LOGIN_MESSAGE);
        res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(objectMapper.writeValueAsString(invalidLogin));
        res.getWriter().flush();
    }
}

package com.aayush.libraryManagementAPIs.model.response;

import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class UserListResponse extends CommonResponse {
    ArrayList<UserResponse> responseData;
}

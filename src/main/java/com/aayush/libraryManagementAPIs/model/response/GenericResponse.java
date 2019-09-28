package com.aayush.libraryManagementAPIs.model.response;

import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenericResponse<T> extends CommonResponse {
    T  responseData;
}

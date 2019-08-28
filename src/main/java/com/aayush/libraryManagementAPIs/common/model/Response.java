package com.aayush.libraryManagementAPIs.common.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter @Setter
public class Response extends CommonResponse{
    private Map<String, Object> response;
    @Override
    public String toString() {
        return "Response [response=" + response + ", responseCode =" + getResponseCode() + ", responseMessage ="
                + getResponseMessage() + "]";
    }
}

package com.aayush.libraryManagementAPIs.common.model;

import com.aayush.libraryManagementAPIs.common.messages.LibraryMessages;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonResponse {
	private Integer responseCode;
	private String responseMessage;
	public CommonResponse(Integer responseCode, String responseMessage)
	{
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	public CommonResponse(){}

	public void setSuccessResponse() {
		responseCode = LibraryMessages.SUCCESS_CODE;
		responseMessage = LibraryMessages.SUCCESS_MESSAGE;
	}

	public void setErrorResponse(int errorCode, String errorMessage) {
		this.responseCode = errorCode;
		this.responseMessage = errorMessage;
	}
}

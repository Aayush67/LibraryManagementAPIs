package com.aayush.libraryManagementAPIs.model.response;

import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import com.aayush.libraryManagementAPIs.model.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
@Getter @Setter
public class BookResponse extends CommonResponse {
     ArrayList<Book> responseData;
}

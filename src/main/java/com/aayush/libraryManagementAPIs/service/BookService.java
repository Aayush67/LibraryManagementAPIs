package com.aayush.libraryManagementAPIs.service;

import com.aayush.libraryManagementAPIs.common.messages.LibraryMessages;
import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import com.aayush.libraryManagementAPIs.common.model.Response;
import com.aayush.libraryManagementAPIs.model.entity.Book;
import com.aayush.libraryManagementAPIs.model.entity.UserBookMapping;
import com.aayush.libraryManagementAPIs.model.enums.Status;
import com.aayush.libraryManagementAPIs.model.response.BookResponse;
import com.aayush.libraryManagementAPIs.repository.BookRepository;
import com.aayush.libraryManagementAPIs.repository.UserBookMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserBookMappingRepository userBookMappingRepository;

    public CommonResponse addBook(Book book)
    {
        bookRepository.save(book);
        return new CommonResponse(LibraryMessages.SUCCESS_CODE,LibraryMessages.SUCCESS_MESSAGE);
    }

    public CommonResponse deleteBook(Long bookId)
    {
        bookRepository.deleteById(bookId);
        return new CommonResponse(LibraryMessages.SUCCESS_CODE,LibraryMessages.SUCCESS_MESSAGE);
    }

    public Book getBook(Long bookId)
    {
        Book book = bookRepository.getBook(bookId);
        return book;
//        return new CommonResponse(LibraryMessages.SUCCESS_CODE,LibraryMessages.SUCCESS_MESSAGE);
    }

    public CommonResponse updateBook(Book book)
    {
        bookRepository.save(book);
        return new CommonResponse(LibraryMessages.SUCCESS_CODE,LibraryMessages.SUCCESS_MESSAGE);
    }

    public BookResponse getAllBooks() {
        ArrayList<Book> bookList = bookRepository.findAll();
        BookResponse bookListResponse = new BookResponse();
        bookListResponse.setResponseMessage(LibraryMessages.SUCCESS_MESSAGE);
        bookListResponse.setResponseCode(LibraryMessages.SUCCESS_CODE);
        bookListResponse.setResponseData(bookList);
        return bookListResponse;
    }

    public CommonResponse borrowBook(UserBookMapping userBookId) {
        if(checkBookBorrowed(userBookId)) {
            this.userBookMappingRepository.save(userBookId);
            Status status = Status.UNAVAILABLE;
            updateBookStatus(userBookId.getBookId(),status);
            return new CommonResponse(LibraryMessages.SUCCESS_CODE, LibraryMessages.SUCCESS_MESSAGE);
        } else
            return new CommonResponse(LibraryMessages.INVALID_BORROW__CODE, LibraryMessages.INVALID_BORROW_MESSAGE);
    }

    public boolean checkBookBorrowed(UserBookMapping userBookId) {
     return this.userBookMappingRepository.checkBookBorrowed(userBookId.getUserId(), userBookId.getBookId())==0 ? true:false;
    }

    public void updateBookStatus(Long bookId, Status status){
        this.bookRepository.updateBookStatus(bookId, status);
    }

    public void getUserBook(Long userId) {
       ArrayList<Book> bookList =  this.bookRepository.getUserBook(userId);
        System.out.println(bookList);
    }
}

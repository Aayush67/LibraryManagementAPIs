package com.aayush.libraryManagementAPIs.service;

import com.aayush.libraryManagementAPIs.common.messages.LibraryMessages;
import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import com.aayush.libraryManagementAPIs.common.model.Response;
import com.aayush.libraryManagementAPIs.model.entity.Book;
import com.aayush.libraryManagementAPIs.model.entity.BookReturnedLogs;
import com.aayush.libraryManagementAPIs.model.entity.UserBookMapping;
import com.aayush.libraryManagementAPIs.model.enums.Status;
import com.aayush.libraryManagementAPIs.model.response.BookResponse;
import com.aayush.libraryManagementAPIs.model.response.GenericResponse;
import com.aayush.libraryManagementAPIs.repository.BookRepository;
import com.aayush.libraryManagementAPIs.repository.BookReturnedLogsRepository;
import com.aayush.libraryManagementAPIs.repository.UserBookMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserBookMappingRepository userBookMappingRepository;

    @Autowired
    private BookReturnedLogsRepository bookReturnedLogsRepository;

    public CommonResponse addBook(Book book)
    {
        if(Objects.isNull(book.getStatus())){
            book.setStatus(Status.AVAILABLE);
        }
        bookRepository.save(book);
        return new CommonResponse(LibraryMessages.SUCCESS_CODE,LibraryMessages.SUCCESS_MESSAGE);
    }

    public CommonResponse deleteBook(Long bookId)
    {
        bookRepository.deleteById(bookId);
        userBookMappingRepository.deleteUserHavingBookId(bookId);
        return new CommonResponse(LibraryMessages.SUCCESS_CODE,LibraryMessages.SUCCESS_MESSAGE);
    }

    public GenericResponse getBookById(Long bookId)
    {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = optionalBook.get();
        GenericResponse<Book> bookRespose = new GenericResponse<>();
        bookRespose.setResponseData(book);
        bookRespose.setResponseCode(LibraryMessages.SUCCESS_CODE);
        bookRespose.setResponseMessage(LibraryMessages.SUCCESS_MESSAGE);
        return bookRespose;
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
            LocalDate borrowDate= LocalDate.now();
            LocalDate returnDate = borrowDate.plusDays(15);
            updateBookStatusDate(userBookId.getBookId(),status,borrowDate, returnDate);
            return new CommonResponse(LibraryMessages.SUCCESS_CODE, LibraryMessages.SUCCESS_MESSAGE);
        } else
            return new CommonResponse(LibraryMessages.INVALID_BORROW__CODE, LibraryMessages.INVALID_BORROW_MESSAGE);
    }

    public CommonResponse returnBook(BookReturnedLogs returnedBook) {
        this.userBookMappingRepository.removeUserBookMapping(returnedBook.getUserId(), returnedBook.getBookId());
        Status status = Status.AVAILABLE;
        updateBookStatusDate(returnedBook.getBookId(),status, null, null);
        bookReturnedLogsRepository.save(returnedBook);
        return new CommonResponse(LibraryMessages.SUCCESS_CODE, LibraryMessages.SUCCESS_MESSAGE);
    }

    public boolean checkBookBorrowed(UserBookMapping userBookId) {
     return this.userBookMappingRepository.checkBookBorrowed(userBookId.getUserId(), userBookId.getBookId())==0 ? true:false;
    }

    public void updateBookStatusDate(Long bookId, Status status, LocalDate borrowDate, LocalDate returnDate){
        this.bookRepository.updateBookStatus(bookId, status, borrowDate == null ? null :Date.valueOf(borrowDate), returnDate == null ? null :Date.valueOf(returnDate));
    }

    public BookResponse getUserBook(Long userId) {
        ArrayList<Book> bookList =  this.bookRepository.getUserBook(userId);
        BookResponse bookListResponse = new BookResponse();
        bookListResponse.setResponseMessage(LibraryMessages.SUCCESS_MESSAGE);
        bookListResponse.setResponseCode(LibraryMessages.SUCCESS_CODE);
        bookListResponse.setResponseData(bookList);
        return bookListResponse;
    }
}

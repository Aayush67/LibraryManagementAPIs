package com.aayush.libraryManagementAPIs.controller;

import com.aayush.libraryManagementAPIs.common.Exception.LibraryException;
import com.aayush.libraryManagementAPIs.common.messages.LibraryMessages;
import com.aayush.libraryManagementAPIs.common.model.CommonResponse;
import com.aayush.libraryManagementAPIs.common.model.Response;
import com.aayush.libraryManagementAPIs.model.entity.Book;
import com.aayush.libraryManagementAPIs.model.entity.UserBookMapping;
import com.aayush.libraryManagementAPIs.model.response.BookResponse;
import com.aayush.libraryManagementAPIs.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/book")
@Api(tags="Book Controller", description = "Add , Delete and Update Book Details")
//@Api(value="Account Controller", description = "Register Student and Teacher")

public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Add Book")
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public CommonResponse addBook(@RequestBody Book book) throws LibraryException
    {
        if (Objects.isNull(book))
            throw new LibraryException(LibraryMessages.INVALID_PARAMETER_CODE, LibraryMessages.INVALID_PARAMETER_MESSAGE);
        return bookService.addBook(book);
    }

    @ApiOperation(value = "Delete Book")
    @RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.DELETE)
    public CommonResponse deleteBook(@PathVariable Long id) throws LibraryException
    {
        return bookService.deleteBook(id);
    }

    @ApiOperation(value = "Return Book")
    @RequestMapping(value = "/getBook/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable Long id) throws LibraryException
    {
        return bookService.getBook(id);
    }

    @ApiOperation(value = "Update Book")
    @RequestMapping(value = "/updateBook", method = RequestMethod.PUT)
    public CommonResponse updateBook(@RequestBody Book book) throws LibraryException
    {
        if (Objects.isNull(book))
            throw new LibraryException(LibraryMessages.INVALID_PARAMETER_CODE, LibraryMessages.INVALID_PARAMETER_MESSAGE);
        return bookService.updateBook(book);
    }

    @ApiOperation(value = "Get All Books")
    @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
    public BookResponse getAllBooks() throws LibraryException
    {
        return bookService.getAllBooks();
    }

    @ApiOperation(value = "Borrow Book")
    @RequestMapping(value = "/borrowBook", method = RequestMethod.POST)
    public CommonResponse borrowBook(@RequestBody UserBookMapping userBookId) throws LibraryException
    {
        if (Objects.isNull(userBookId))
            throw new LibraryException(LibraryMessages.INVALID_PARAMETER_CODE, LibraryMessages.INVALID_PARAMETER_MESSAGE);
        return bookService.borrowBook(userBookId);
    }

    @ApiOperation(value = "Get Book Borrowed By User")
    @RequestMapping(value = "/getUserBook", method = RequestMethod.GET)
    public void getUserBook(@RequestParam("id") Long userId) throws LibraryException
    {
        if (Objects.isNull(userId))
            throw new LibraryException(LibraryMessages.INVALID_PARAMETER_CODE, LibraryMessages.INVALID_PARAMETER_MESSAGE);
         bookService.getUserBook(userId);
    }
}

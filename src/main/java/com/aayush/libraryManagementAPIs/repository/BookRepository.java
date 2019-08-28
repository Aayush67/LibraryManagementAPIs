package com.aayush.libraryManagementAPIs.repository;

import com.aayush.libraryManagementAPIs.model.entity.Book;
import com.aayush.libraryManagementAPIs.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book,Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE from Book b WHERE b.id =?1")
    void deleteBook(Long bookid);

    void deleteById(Long bookid);

    @Query(value = "SELECT b FROM Book b WHERE b.id =?1")
    Book getBook(Long bookid);

    ArrayList<Book> findAll();

    @Transactional
    @Modifying
    @Query("update Book book set book.status = ?2 where book.id = ?1")
    void updateBookStatus(Long bookId, Status status);

    @Query(value = "select user_book_mapping.id from user_book_mapping inner JOIN book  on user_book_mapping.user_id= ?1 and user_book_mapping.book_id= book.id", nativeQuery = true)
    ArrayList<Book> getUserBook(Long userId);
}

package com.aayush.libraryManagementAPIs.repository;

import com.aayush.libraryManagementAPIs.model.entity.UserBookMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookMappingRepository extends JpaRepository<UserBookMapping, Long> {

    @Query(value = "select count(book) from UserBookMapping book where book.userId = ?1 and book.bookId = ?2")
    int checkBookBorrowed(Long userId, Long bookId);
}

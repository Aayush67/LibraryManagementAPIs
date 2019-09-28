package com.aayush.libraryManagementAPIs.repository;

import com.aayush.libraryManagementAPIs.model.entity.UserBookMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserBookMappingRepository extends JpaRepository<UserBookMapping, Long> {

    @Query(value = "select count(ubm) from UserBookMapping ubm where ubm.userId = ?1 and ubm.bookId = ?2")
    int checkBookBorrowed(Long userId, Long bookId);

    @Transactional
    @Modifying
    @Query("delete from UserBookMapping ubm where ubm.userId = ?1 and ubm.bookId = ?2")
    void removeUserBookMapping(Long userId, Long bookId);

    @Transactional
    @Modifying
    @Query("delete from UserBookMapping ubm where ubm.userId = ?1")
    void deleteUserById(Long userId);

    @Transactional
    @Modifying
    @Query("delete from UserBookMapping ubm where ubm.bookId = ?1")
    void deleteUserHavingBookId(Long userId);

    @Query("select ubm from UserBookMapping ubm where ubm.userId = ?1")
    List<UserBookMapping> getUserIdAndBookId(Long userId);

}

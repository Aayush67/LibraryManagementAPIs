package com.aayush.libraryManagementAPIs.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class UserBookMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long bookId;
    public UserBookMapping() {}
    public UserBookMapping(Long userId, Long bookId, int count)
    {
        this.userId = userId;
        this.bookId = bookId;
    }
}

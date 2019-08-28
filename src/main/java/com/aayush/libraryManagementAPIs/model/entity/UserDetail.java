package com.aayush.libraryManagementAPIs.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
public class UserDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;
    private String username;
    private String phone;
    private String address;
    private String age;
 /*    @OneToMany
    @JoinColumn
    private Set<Book> books;
   @JoinTable(name= "user_book_mapping", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;*/
}



package com.aayush.libraryManagementAPIs.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "book_returned_logs")
@Getter @Setter
public class BookReturnedLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long bookId;
    private Long userId;
    private Long fine;
    private Date returnedDate;
    private Date issuedDate;
}

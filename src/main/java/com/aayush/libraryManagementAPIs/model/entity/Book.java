package com.aayush.libraryManagementAPIs.model.entity;

import com.aayush.libraryManagementAPIs.model.enums.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("Name Of Book")
    private String name;
    private String publication;
    private String author;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int published_year;
    private Date issueDate;
    private Date returnDate;
}

package com.lionel.bookmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table( name = "books" )
@Data
public class Book 
{

    @Id
    @Column( name = "book_code" ) 
    @NotEmpty( message = "Book Code must not be empty!" )
    private String bookCode;

    @Column( name = "book_author")
    @NotEmpty( message = "Book Author must not be empty!" )
    private String bookAuthor; 

    @Column( name = "book_price" )
    @NotEmpty( message = "Book Price must not be empty!" )
    private String bookPrice;

    @Column( name = "book_title" )
    @NotEmpty( message = "Book Title must not be empty!" )
    private String bookTitle;

    @CreatedDate
    @Column( name = "created_at" )
    private Date createdAt;

}

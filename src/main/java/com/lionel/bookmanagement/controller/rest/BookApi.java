package com.lionel.bookmanagement.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lionel.bookmanagement.model.Book;
import com.lionel.bookmanagement.model.HttpResponse;
import com.lionel.bookmanagement.service.BookService;

@RestController
@RequestMapping( "/api" )
@CrossOrigin( value = "http://localhost:3000" )
public class BookApi {

    @Autowired
    BookService bookService;

    @GetMapping( value = "/books" )
    public List<Book> getBooks(){
        return bookService.findAll();
    }

    @GetMapping( value = "/books/{id}" )
    public Book getBook( @PathVariable("id") String id ){
        return bookService.findById( id );
    }

    @PostMapping( value =  "/books" )
    public HttpResponse postCreateBook( @RequestBody Book book ){
        HttpResponse res = new HttpResponse();

        if( bookService.findById( book.getBookCode()) != null ){
            res.setHttpStatusCode( 208 );
            res.setStatus( HttpStatus.ALREADY_REPORTED);
            res.setMsg("Book Code must be unique!");
        }

        if( bookService.save( book ) > 0 ){
            res.setHttpStatusCode( 201 );
            res.setStatus( HttpStatus.OK );
            res.setMsg("Successfully Created!");
        }

        return  res;
    }

    @DeleteMapping( value = "/books/{id}")
    public HttpResponse deleteBook( @PathVariable("id") String id ){
        HttpResponse res = new HttpResponse();

        if( bookService.deleteOne( id ) > 0 ){
            res.setHttpStatusCode( 202 );
            res.setStatus( HttpStatus.OK );
            res.setMsg( "Successfully Deleted!");
        }

        return res;

    }

    @PutMapping( value = "/books/{id}" )
    public HttpResponse updateBook( @PathVariable("id") String id , @RequestBody Book book  ){

        HttpResponse res = new HttpResponse();

        if( bookService.updateOne(book) > 0 ){
            res.setHttpStatusCode( 202 );
            res.setStatus( HttpStatus.OK );
            res.setMsg("Successfully Updated!");
        }

        return res;

    }

}

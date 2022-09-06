package com.lionel.bookmanagement.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lionel.bookmanagement.model.Book;
import com.lionel.bookmanagement.service.BookService;

@Controller
public class BookController {
    
    @Autowired
    BookService bookService;

    @GetMapping( value = "/" )
    public String getHome()
    {
        return "home";
    }

    @GetMapping( value = "/books" )
    public String getBooksPage( ModelMap model )
    {
        model.addAttribute( "books", bookService.findAll());
        return "books";
    }

    @GetMapping( value = "/books/new" )
    public ModelAndView getBookCreatePage()
    {
        return new ModelAndView( "addbook" , "book" , new Book() );
    }

    @PostMapping( value = "/books/new" )
    public String postCreateBook( @ModelAttribute("book") @Validated Book book , BindingResult bind , ModelMap model )
    {
        if(bind.hasErrors())
        {
            model.addAttribute( "book" , book );
            return "addbook";
        }

        // Book foundBook = bookService.findById( book.getBookCode() );

        // if( foundBook != null )
        // {
        //     model.addAttribute( "book", book );
        //     model.addAttribute( "error", "Book code must be unique!");
        //     return "addbook";
        // }

        book.setCreatedAt( new Date() );

        int status = bookService.save( book );
        
        if( status == 0 )
        {
            model.addAttribute( "book", book );
            model.addAttribute( "error", "Duplicate Book Code!");
            return "addbook";
        }
        return "redirect:/books?msg=Successfully Added!";
    }

    @GetMapping( value = "/books/{id}/delete" )
    public String getDeleteBook(@PathVariable("id") String bookCode )
    {
        int status = bookService.deleteOne( bookCode );
        
        String msg = "";

        if( status > 0 ) msg = "Successfully Deleted!";
        else msg = "Something went wrong!&?error=true"; 

        return "redirect:/books?msg="+msg;
    }

    @GetMapping( value = "/books/{id}" )
    public String getBookPage(@PathVariable("id") String id , ModelMap model )
    {
        Book book = bookService.findById( id );

        if( book == null ) return "redirect:/books?msg=Something went wrong!&error=true";

        model.addAttribute("book", book );
        return "editbook";

    }

    @PostMapping( value = "/books/{id}" )
    public String postBookUpdate( @PathVariable("id") String id , @ModelAttribute("book") @Validated Book book , BindingResult bind , ModelMap model )
    {
        if( bind.hasErrors() )
        {
            model.addAttribute( "book", book );
            return "editbook"; 
        }

        int status = bookService.updateOne( book );

        String msg = "";

        System.out.println("here");

        if( status > 0 ) msg = "Successfully Updated!";
        else msg = "Something went wrong!&error=true";
        
        return "redirect:/books?msg="+msg;
    }

}

package com.lionel.bookmanagement.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.lionel.bookmanagement.model.Book;
import com.lionel.bookmanagement.repository.BookRepository;
import com.lionel.bookmanagement.service.BookService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestBookController {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @MockBean
    BookRepository bookRepo;

    @Test
    public void testGetHome() throws Exception{
        this.mockMvc.perform( get("/") )
        .andExpect(status().isOk())
        .andExpect(view().name("home"));
    
    }

    @Test
    public void testGetBookCreatePage() throws Exception {
        this.mockMvc.perform( get("/books/new") )
        .andExpect( status().isOk())
        .andExpect( view().name("addbook") )
        .andExpect( model().attributeExists("book"));
    }

    @Test
    public void  testPostBookCreateWithBlank() throws Exception {
        this.mockMvc.perform( post("/books/new") )
        .andExpect( status().isOk() )
        .andExpect( view().name("addbook") )
        .andExpect( model().attributeExists("book"));
    }

    @Test
    public void testPostBookCreate() throws Exception {

        Book book = new Book();
        book.setBookAuthor("author");
        book.setBookCode("B001");
        book.setBookPrice("1000");
        book.setBookTitle("title");

        this.mockMvc.perform( post("/books/new").flashAttr("book", book))
        .andExpect( status().is(200))
        .andExpect(redirectedUrl("/books"));
    }

    @Test
    public void testGetBookDeleteFail() throws Exception {
        this.mockMvc.perform( get("/books/{id}/delete" , "B001") )
        .andExpect( status().is(302) )
        .andExpect(redirectedUrl("/books?msg=Something went wrong!&error=true"));
    }

    @Test
    public void testGetBookDelete() throws Exception {
        this.mockMvc.perform( get("/books/{id}/delete" , "B001") )
        .andExpect( status().is(302))
        .andExpect(redirectedUrl("/books"));
    }

    @Test
    public void testGetBookFail() throws Exception {
        this.mockMvc.perform( get("/books/{id}" , "B001") )
        .andExpect( status().is( 302 ) ) 
        .andExpect( redirectedUrl("/books?msg=Something went wrong!&error=true") );
    }

    @Test 
    public void testGetBook() throws Exception {
        this.mockMvc.perform( get("/books/{id}" , "B001") )
        .andExpect( status().is(302) ) 
        .andExpect(view().name("editbook"))
        .andExpect( model().attributeExists("book"));
    }

    @Test
    public void testPostBookUpdate () throws Exception {

        Book book = new Book();
        book.setBookAuthor("author");
        book.setBookCode("B001");
        book.setBookPrice("1000");
        book.setBookTitle("title");

        this.mockMvc.perform( post("/books/{id}" , "B001").flashAttr("book", book) )
        .andExpect( status().is(302) )
        .andExpect( view().name("editbook") )
        .andExpect( model().attributeExists("book"));
    }

    @Test
    public void testPostBookUpdateFail() throws Exception {

        Book book = new Book();
        book.setBookAuthor("author");
        book.setBookCode("B001");
        book.setBookPrice("1000");
        book.setBookTitle("title");


        this.mockMvc.perform( post("/books/{id}" , "B001").flashAttr("book", book ) )
        .andExpect( status().is(302))
        .andExpect( redirectedUrl("/books?msg=Something went wrong!&error=true"));
    }


}

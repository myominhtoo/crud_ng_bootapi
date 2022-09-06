package com.lionel.bookmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.lionel.bookmanagement.model.Book;
import com.lionel.bookmanagement.repository.BookRepository;


@SpringBootTest
public class TestBookService {
    
    @Mock
    BookRepository bookRepo;

    @InjectMocks
    BookService bookService;

    @Test
    public void testSave(){

        Book book = new Book();
        book.setBookCode( "B001" );
        book.setBookAuthor("author");
        book.setBookPrice("1000");
        book.setBookTitle("title");

        bookRepo.save( book );
        verify( bookRepo , times( 1 )).save(book);
    }

    @Test
    public void testDeleteOne(){
        bookService.deleteOne("B001");
        verify( bookRepo , times(1)).deleteById("B001");
    }

    @Test
    public void testFindAll(){
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setBookCode( "B001" );
        b1.setBookAuthor("author");
        b1.setBookPrice("1000");
        b1.setBookTitle("title");

        Book b2 = new Book();
        b2.setBookCode( "B001" );
        b2.setBookAuthor("author");
        b2.setBookPrice("1000");
        b2.setBookTitle("title");

        books.add( b1 );
        books.add( b2 );

        when( bookRepo.findAll() ).thenReturn( books );
        List<Book> foundBooks = bookService.findAll();
        assertEquals( 2 , foundBooks.size() );
        verify( bookRepo , times(1)).findAll();

    }

    @Test
    public void testFindById(){
        Book book = new Book();
        book.setBookAuthor("author1");
        book.setBookCode("B001");
        book.setBookPrice("1000");
        book.setBookTitle("title1");

        Optional<Book> savedBook = Optional.of( book );

        when( bookRepo.findById("B001")).thenReturn( savedBook );
        
        Book foundBook = bookService.findById("B001");
        assertEquals( book , foundBook );
        
    }

    @Test
    public void testUpdateOne(){

        Book book = new Book();
        book.setBookAuthor("author1");
        book.setBookCode("B001");
        book.setBookPrice("1000");
        book.setBookTitle("title");

        Optional<Book> returnBook = Optional.of( book );
        
        when( bookRepo.findById("B001")).thenReturn( returnBook );

        Book savedBook = bookService.findById("B001");
        savedBook.setBookAuthor("author update");

        bookRepo.save( savedBook );
        verify( bookRepo , times(1)).save( savedBook );
    
    }

}

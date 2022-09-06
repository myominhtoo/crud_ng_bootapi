package com.lionel.bookmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lionel.bookmanagement.model.Book;
import com.lionel.bookmanagement.repository.BookRepository;

@Service
public class BookService {
    
    @Autowired
    BookRepository repo;

    public int save( Book book  ){
        Optional<Book> savedBooks = repo.findById( book.getBookCode());

        int status = 0;

        if( savedBooks.isEmpty()){
            repo.save( book );
            status = 1;
        }

        return status;
    }

    public int deleteOne( String bookCode ){
    
        int status = 0;

        try{
            repo.deleteById( bookCode );     
            status = 1;
        }catch( Exception e){
            status = 0;
        }   

        return status;

    }

    public Book findById( String bookCode ){
       Optional<Book> savedBook =   repo.findById( bookCode );
        return savedBook.isPresent() ? savedBook.get() : null ;
    }

    public int updateOne( Book book ){
       Book savedBook = repo.findById( book.getBookCode() ).get();

       savedBook.setBookAuthor( book.getBookAuthor() );
       savedBook.setBookPrice( book.getBookPrice() );
       savedBook.setBookTitle( book.getBookTitle() );

       int status = 0;

       if( repo.save( savedBook ) != null ){
         status = 1;
       }
       
       return status;

    }


    public List<Book> findAll(){
        return repo.findAll();
    }

}

package com.lionel.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lionel.bookmanagement.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    
}

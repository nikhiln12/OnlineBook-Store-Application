package com.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	
}

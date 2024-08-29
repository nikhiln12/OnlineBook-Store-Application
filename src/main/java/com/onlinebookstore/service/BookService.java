package com.onlinebookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
    @Cacheable(value = "allBooks")
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
    @Cacheable(value = "books", key = "#id")
	public Optional<Book> getBookById(long id) {
		return bookRepository.findById(id);
	}
	
    @CachePut(value = "books", key = "#result.id")
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
    @CacheEvict(value = "books", key = "#id")
	public void deleteBook(long id) {
		 bookRepository.deleteById(id);
	}
	
    @CachePut(value = "books", key = "#id")
    public Book updateBook(long id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        }
        return null;
    }

}

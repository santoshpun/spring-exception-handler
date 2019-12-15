package com.santosh.exceptionhandler.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.exceptionhandler.exception.RecordNotFoundException;
import com.santosh.exceptionhandler.model.Book;
import com.santosh.exceptionhandler.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks() {
		List<Book> books = bookRepository.findAll();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching book with id " + id);

		Book book = bookRepository.findById(id);
		if (book == null) {
			throw new RecordNotFoundException("Book with id " + id + " not found");
		}

		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public ResponseEntity<?> addBook(@Valid @RequestBody Book book) {
		bookRepository.save(book);
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}
}

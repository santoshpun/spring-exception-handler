package com.santosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.model.Book;
import com.santosh.model.BookList;
import com.santosh.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping(path = "/", produces = "application/json")
	public BookList getAllBooks() {

		BookList bookList = new BookList();

		List<Book> books = bookRepository.findAll();

		bookList.setBooks(books);

		return bookList;
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks() {
		List<Book> books = bookRepository.findAll();

		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching book with id " + id);

		Book book = bookRepository.findById(id);

		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@RequestMapping(value = "/books/", method = RequestMethod.POST)
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		bookRepository.save(book);

		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}

}

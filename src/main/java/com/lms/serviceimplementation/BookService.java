package com.lms.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.dto.BookDto;
import com.lms.entity.Book;
import com.lms.entity.Book;
import com.lms.exception.BookIdNotFoundException;
import com.lms.exception.BookNotFoundException;
import com.lms.exception.BookIdNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.service.IBookService;
import com.lms.until.ApiResponse;

@Service
public class BookService implements IBookService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	BookRepository bookRepository;

	@Override
	public ResponseEntity<ApiResponse<Book>> saveBoook(BookDto bookDto) {
		ApiResponse<Book> apiResponse = new ApiResponse<>();

		Book book = modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);

		apiResponse.setMessage("Address Saved Successfully.");
		apiResponse.setData(book);
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
		return new ResponseEntity<ApiResponse<Book>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> updateBook(BookDto bookDto) {
		ApiResponse<Book> apiResponse = new ApiResponse<>();

		Book book = modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);

		apiResponse.setMessage("Book Updated Successfully");
		apiResponse.setData(book);
		apiResponse.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ApiResponse<Book>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> findBookById(int BookId) {
		ApiResponse<Book> apiResponse = new ApiResponse<>();

		Optional<Book> optional = bookRepository.findById(BookId);

		if (optional.isPresent()) {
			Book book = optional.get();

			apiResponse.setMessage("Book Found Successfully Book Id: " + book.getBookId() + ".");
			apiResponse.setData(book);
			apiResponse.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ApiResponse<Book>>(apiResponse, HttpStatus.OK);
		}

		throw new BookIdNotFoundException("Invalid Book Id!!!");
	}

	@Override
	public ResponseEntity<ApiResponse<List<Book>>> findAllBook() {
		ApiResponse<List<Book>> apiResponse = new ApiResponse<>();

		List<Book> bookList = bookRepository.findAll();

		if (bookList != null && !bookList.isEmpty()) {

			apiResponse.setMessage("Addresses Found Successfully.");
			apiResponse.setData(bookList);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<List<Book>>>(apiResponse, HttpStatus.OK);
		}

		throw new BookNotFoundException("Book DB Empty!!!");


	}

	@Override
	public ResponseEntity<ApiResponse<Book>> deleteBookById(int BookId) {
		ApiResponse<Book> apiResponse = new ApiResponse<>();

		Optional<Book> optional = bookRepository.findById(BookId);

		if (optional.isPresent()) {
			Book book = optional.get();
			bookRepository.delete(book);

			apiResponse.setMessage("Book Deleted Successfully.");
			apiResponse.setData(book);
			apiResponse.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ApiResponse<Book>>(apiResponse, HttpStatus.OK);

		}
		throw new BookIdNotFoundException("Invalid Book Id!!!");

	}

	public ResponseEntity<ApiResponse<Book>> updateBookPartially(BookDto bookDto, int bookId){
		Optional<Book> optional = bookRepository.findById(bookId);
		
		ApiResponse<Book> apiResponse = new ApiResponse<>();
		
		if(optional.isPresent()) {
			Book book = optional.get();
			
			book.setTitle(bookDto.getTitle());
			
			bookRepository.save(book);
			
			apiResponse.setMessage("Book Updated Successfully.");
			apiResponse.setData(book);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
					
			return new ResponseEntity<ApiResponse<Book>>(apiResponse, HttpStatus.OK);
		}else {
			throw new BookIdNotFoundException("Invalid Book Id!!!");
		}
	}
	
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(String author){
		List<Book> bookList = bookRepository.findByAuthor(author);
		ApiResponse<List<BookResponse>> apiResponse = new ApiResponse<>();

		List<BookResponse> bookResponses= new ArrayList<>();
		
		for(Book book : bookList) {
			bookResponses.add(modelMapper.map(book, BookResponse.class));
		}
		
		apiResponse.setMessage("Book Fetched Based On Author.");
		apiResponse.setData(bookResponses);
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
				
		return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByTitle(String title) {
		List<Book> bookList = bookRepository.findByTitle(title);
		ApiResponse<List<BookResponse>> apiResponse = new ApiResponse<>();

		List<BookResponse> bookResponses= new ArrayList<>();
		
		for(Book book : bookList) {
			bookResponses.add(modelMapper.map(book, BookResponse.class));
		}
		
		apiResponse.setMessage("Book Fetched Based On Title.");
		apiResponse.setData(bookResponses);
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
				
		return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthorAndTitle(String author, String title) {
		List<Book> bookList = bookRepository.findByAuthorAndTitle(author, title);
		ApiResponse<List<BookResponse>> apiResponse = new ApiResponse<>();

		List<BookResponse> bookResponses= new ArrayList<>();
		
		for(Book book : bookList) {
			bookResponses.add(modelMapper.map(book, BookResponse.class));
		}
		
		apiResponse.setMessage("Book Fetched Based On Author.");
		apiResponse.setData(bookResponses);
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
				
		return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponse, HttpStatus.OK);
	}
	
	
	
}

package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Book;
import com.lms.until.ApiResponse;

public interface IBookService {
	
	// 1. Save Book
	public ResponseEntity<ApiResponse<Book>> saveBoook(BookDto bookDto);
	
	// 2. Update Book
	public ResponseEntity<ApiResponse<Book>> updateBook(BookDto bookDto);
	
	// 3. Find Book By Id
	public ResponseEntity<ApiResponse<Book>> findBookById(int BookId);
	
	// 4. Find All Book
	public ResponseEntity<ApiResponse<List<Book>>> findAllBook();
	
	// 5. Delete Book
	public ResponseEntity<ApiResponse<Book>> deleteBookById(int BookId);
	
	public ResponseEntity<ApiResponse<Book>> updateBookPartially(BookDto bookDto, int bookId);

	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(String author);

	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByTitle(String title);
	
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthorAndTitle(String author, String title);
}

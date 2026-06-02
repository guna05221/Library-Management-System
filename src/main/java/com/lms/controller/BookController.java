package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.dto.BookDto;
import com.lms.entity.Book;
import com.lms.entity.Book;
import com.lms.service.IBookService;
import com.lms.until.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	IBookService bookService;

	// 1. Save Book
	@Operation(
	        operationId = "CreateBook",
	        summary = "Adding one book",
	        description = "This REST endpoint is used to create and add one book"
	    )
	    @ApiResponses(value = {
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "200",
	            description = "Book created successfully"
	        )
	    })
	@PostMapping
	public ResponseEntity<ApiResponse<Book>> saveBoook(@RequestBody BookDto bookDto) {
		return bookService.saveBoook(bookDto);
	}

	// 2. Update Book
	@Operation(
	        operationId = "UpdateBook",
	        summary = "Updating one book",
	        description = "This REST endpoint is used to update book details"
	    )
	    @ApiResponses(value = {
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "200",
	            description = "Book updated successfully"
	        )
	    })
	@PutMapping
	public ResponseEntity<ApiResponse<Book>> updateBook(@RequestBody BookDto bookDto){
		return bookService.updateBook(bookDto);
	}

	// 3. Find Book By Id
	@Operation(
	        operationId = "FetchBookById",
	        summary = "Fetching one book",
	        description = "This REST endpoint is used to fetch book using bookId"
	    )
	    @ApiResponses(value = {
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "200",
	            description = "Book fetched successfully"
	        ),
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "400",
	            description = "Invalid book id"
	        )
	    })
	@GetMapping("/{BookId}")
	public ResponseEntity<ApiResponse<Book>> findBookById(@PathVariable int BookId){
		return bookService.findBookById(BookId);
	}

	// 4. Find All Book
	@Operation(
	        operationId = "FetchAllBooks",
	        summary = "Fetching all books",
	        description = "This REST endpoint is used to fetch all books"
	    )
	    @ApiResponses(value = {
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "200",
	            description = "Books fetched successfully"
	        ),
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "400",
	            description = "Book database is empty"
	        )
	    })
	@GetMapping
	public ResponseEntity<ApiResponse<List<Book>>> findAllBook(){
		return bookService.findAllBook();
	}

	// 5. Delete Book
	 @Operation(
		        operationId = "DeleteBook",
		        summary = "Deleting one book",
		        description = "This REST endpoint is used to delete book using bookId"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Book deleted successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid book id"
		        )
		    })
	@DeleteMapping("/{BookId}")
	public ResponseEntity<ApiResponse<Book>> deleteBookById(@PathVariable int BookId){
		return bookService.deleteBookById(BookId);
	}
	
	 @Operation(
		        operationId = "PartialUpdateBook",
		        summary = "Partially updating one book",
		        description = "This REST endpoint is used to partially update book details"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Book partially updated successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid book id"
		        )
		    })
	@PatchMapping("/{BookId}")
	public ResponseEntity<ApiResponse<Book>> updateBookPartially(@RequestBody BookDto dto,@PathVariable int BookId){
		return bookService.updateBookPartially(dto, BookId);
	}
	
	 @Operation(
		        operationId = "FetchBooksByAuthor",
		        summary = "Fetching books by author",
		        description = "This REST endpoint is used to fetch books using author name"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Books fetched successfully based on author"
		        )
		    })
	@GetMapping("/author/{author}")
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(@PathVariable String author){
		return bookService.displayBookByAuthor(author);
	}

	 @Operation(
		        operationId = "FetchBooksByTitle",
		        summary = "Fetching books by title",
		        description = "This REST endpoint is used to fetch books using title"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Books fetched successfully based on title"
		        )
		    })
	@GetMapping("/title/{title}")
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByTitle(@PathVariable String title){
		return bookService.displayBookByTitle(title);
	}
	
	 @Operation(
		        operationId = "FetchBooksByAuthorAndTitle",
		        summary = "Fetching books by author and title",
		        description = "This REST endpoint is used to fetch books using author and title"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Books fetched successfully based on author and title"
		        )
		    })
	@GetMapping("/authorTitle/{author}/{title}")
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthorAndTitle(@PathVariable String author, @PathVariable String title){
		return bookService.displayBookByAuthorAndTitle(author, title);
	}


}

package com.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lms.until.ApiResponse;

@ControllerAdvice // helps to recognize this class as exception handling class
public class LibraryManagementExceptionHandler {

	// Address
	
	@ExceptionHandler(AddressIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleAddressIdNotFoundException(AddressIdNotFoundException exception){
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Address Id Not Found!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> AddressNotFoundException(AddressNotFoundException exception) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Address Not Found!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	// Book
	
	@ExceptionHandler(BookIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleBookIdNotFoundException(BookIdNotFoundException exception){
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Book Id Not Found!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> BookNotFoundException(BookNotFoundException exception) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Book Not Found!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	// Library
	
	@ExceptionHandler(LibraryIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleLibraryIdNotFoundException(LibraryIdNotFoundException exception){
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Library Id Not Found!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(LibraryNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> LibraryNotFoundException(LibraryNotFoundException exception) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Library Not Found!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	// User
	
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleUserIdNotFoundException(UserIdNotFoundException exception){
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("User Id Not Found!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> UserNotFoundException(UserNotFoundException exception) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("User Not Found!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BookUnableToAddLibraryException.class)
	public ResponseEntity<ApiResponse<String>> handleBookUnableToAddLibraryException(BookUnableToAddLibraryException exception){
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Library Id/ Book Id not available!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookUnableToBorrowException.class)
	public ResponseEntity<ApiResponse<String>> handleBookUnableToBorrowException(BookUnableToBorrowException exception){
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Invalid User Id/ Book Id Or Book Already Borrowed!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<ApiResponse<String>> handleBookUnableToReturnException(BookUnableToReturnException exception){
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Already Returned / Not Borrowed!!!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
}

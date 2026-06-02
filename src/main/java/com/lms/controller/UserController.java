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

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.service.IUserService;
import com.lms.until.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;
	
	@Operation(
	        operationId = "CreateUser",
	        summary = "Adding one user",
	        description = "This REST endpoint is used to create and save one user using addressId"
	    )
	    @ApiResponses(value = {
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "200",
	            description = "User created successfully"
	        ),
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "400",
	            description = "Invalid address id"
	        )
	    })
	@PostMapping("/{addressId}")
	public ResponseEntity<ApiResponse<User>> saveUser(@RequestBody UserDto userDto,@PathVariable int addressId){
		return userService.saveUser(userDto, addressId);
	}
	
//	@PutMapping
//	public ResponseEntity<ApiResponse<User>> updateUser(@RequestBody UserDto userDto){
//		return userService.updateUser(userDto);
//	}
	
	 @Operation(
		        operationId = "FetchUserById",
		        summary = "Fetching one user",
		        description = "This REST endpoint is used to fetch user using userId"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "User fetched successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid user id"
		        )
		    })
	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponse<User>> findUserById(@PathVariable int userId){
		return userService.findUserById(userId);
	}
	
	 @Operation(
		        operationId = "FetchAllUsers",
		        summary = "Fetching all users",
		        description = "This REST endpoint is used to fetch all users"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Users fetched successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "User database is empty"
		        )
		    })
	@GetMapping
	public ResponseEntity<ApiResponse<List<User>>> findAllUser(){
		return userService.findAllUser();
	}
	
	 @Operation(
		        operationId = "DeleteUser",
		        summary = "Deleting one user",
		        description = "This REST endpoint is used to delete user using userId"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "User deleted successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid user id"
		        )
		    })
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse<User>> deleteUserById(@PathVariable int userId){
		return userService.deleteUserById(userId);
	}
	
	 @Operation(
		        operationId = "CompleteUpdateUser",
		        summary = "Completely updating one user",
		        description = "This REST endpoint is used to completely update user details"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "User updated successfully"
		        )
		    })
	@PutMapping
	public ResponseEntity<ApiResponse<User>> updateUserComplete(@RequestBody UserDto UserDto){
		return userService.updateUserComplete(UserDto);
	}
	
	 @Operation(
		        operationId = "PartialUpdateUser",
		        summary = "Partially updating one user",
		        description = "This REST endpoint is used to partially update user details"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "User partially updated successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid user id"
		        )
		    })
	@PatchMapping("/{userId}")
	public ResponseEntity<ApiResponse<User>> updateUserPartially(@RequestBody UserDto UserDto,@PathVariable int userId){
		return userService.updateUserPartially(UserDto, userId);
	}
	
	 @Operation(
		        operationId = "BorrowBook",
		        summary = "Borrowing one book",
		        description = "This REST endpoint is used for user to borrow a book"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Book borrowed successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid user id, invalid book id, or book already borrowed"
		        )
		    })
	@PutMapping("/borrow/{userId}/{BookId}")
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(@PathVariable int userId,@PathVariable int BookId){
		return userService.borrowBookByUser(userId, BookId);
	}
	
	 @Operation(
		        operationId = "ReturnBook",
		        summary = "Returning one book",
		        description = "This REST endpoint is used for user to return a borrowed book"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Book returned successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Book already returned or not borrowed"
		        )
		    })
	@PutMapping("/return/{userId}/{BookId}")
	public ResponseEntity<ApiResponse<User>> returnBookByUser(@PathVariable int userId,@PathVariable int BookId){
		return userService.returnBookByUser(userId, BookId);
	}
}

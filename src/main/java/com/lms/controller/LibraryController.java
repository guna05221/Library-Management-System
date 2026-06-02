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

import com.lms.dto.LibraryDto;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.service.ILibraryService;
import com.lms.until.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	ILibraryService libraryService;
	
	@Operation(
	        operationId = "CreateLibrary",
	        summary = "Adding one library",
	        description = "This REST endpoint is used to create and save one library using addressId"
	    )
	    @ApiResponses(value = {
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "200",
	            description = "Library created successfully"
	        ),
	        @io.swagger.v3.oas.annotations.responses.ApiResponse(
	            responseCode = "400",
	            description = "Invalid address id"
	        )
	    })
	@PostMapping("/{addressId}")
	public ResponseEntity<ApiResponse<Library>> saveLibrary(@RequestBody LibraryDto libraryDto,@PathVariable int addressId){
		return libraryService.saveLibrary(libraryDto, addressId);
	}
	
	 @Operation(
		        operationId = "UpdateLibrary",
		        summary = "Updating one library",
		        description = "This REST endpoint is used to update library details"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Library updated successfully"
		        )
		    })
	@PutMapping
	public ResponseEntity<ApiResponse<Library>> updateLibrary(@RequestBody LibraryDto libraryDto){
		return libraryService.updateLibrary(libraryDto);
	}
	
	 @Operation(
		        operationId = "FetchLibraryById",
		        summary = "Fetching one library",
		        description = "This REST endpoint is used to fetch library using libraryId"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Library fetched successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid library id"
		        )
		    })
	@GetMapping("/{libraryId}")
	public ResponseEntity<ApiResponse<Library>> findLibraryById(@PathVariable int libraryId){
		return libraryService.findLibraryById(libraryId);
	}
	
	 @Operation(
		        operationId = "FetchAllLibraries",
		        summary = "Fetching all libraries",
		        description = "This REST endpoint is used to fetch all libraries"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Libraries fetched successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Library database is empty"
		        )
		    })
	@GetMapping
	public ResponseEntity<ApiResponse<List<Library>>> findAllLibrary(){
		return libraryService.findAllLibrary();
	}
	
	 @Operation(
		        operationId = "DeleteLibrary",
		        summary = "Deleting one library",
		        description = "This REST endpoint is used to delete library using libraryId"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Library deleted successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid library id"
		        )
		    })
	@DeleteMapping("/{libraryId}")
	public ResponseEntity<ApiResponse<Library>> deleteLibraryById(@PathVariable int libraryId){
		return libraryService.deleteLibraryById(libraryId);
	}
	
	 @Operation(
		        operationId = "PartialUpdateLibrary",
		        summary = "Partially updating one library",
		        description = "This REST endpoint is used to partially update library details"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Library partially updated successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid library id"
		        )
		    })
	@PatchMapping("/{libraryId}")
	public ResponseEntity<ApiResponse<Library>> updateLibraryPartially(@RequestBody LibraryDto dto,@PathVariable int libraryId){
		return libraryService.updateLibraryPartially(dto, libraryId);
	}
	
	 @Operation(
		        operationId = "AddBookToLibrary",
		        summary = "Adding one book to library",
		        description = "This REST endpoint is used to add one book to a library"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Book added to library successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid library id or book id"
		        )
		    })
	@PutMapping("/{libraryId}/{BookId}")
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(@PathVariable int libraryId,@PathVariable int BookId){
		return libraryService.addBookToLibrary(libraryId, BookId);
	}
	
	 @Operation(
		        operationId = "DisplayBooksFromLibrary",
		        summary = "Displaying books from library",
		        description = "This REST endpoint is used to fetch all books from a library"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Books fetched successfully from library"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Library not found or no books available"
		        )
		    })
	@GetMapping("display/{libraryId}")
	public ResponseEntity<ApiResponse<List<Book>>> displayBookFromLibrary(@PathVariable int libraryId){
		return libraryService.displayBookFromLibrary(libraryId);
	}
	
}

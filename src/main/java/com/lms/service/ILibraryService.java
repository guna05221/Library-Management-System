package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.LibraryDto;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.until.ApiResponse;

@Service
public interface ILibraryService {
	
	public ResponseEntity<ApiResponse<Library>> saveLibrary(LibraryDto libraryDto, int addressId);
	
	public ResponseEntity<ApiResponse<Library>> updateLibrary(LibraryDto libraryDto);

	public ResponseEntity<ApiResponse<Library>> findLibraryById(int libraryId);
	
	public ResponseEntity<ApiResponse<List<Library>>> findAllLibrary();
	
	public ResponseEntity<ApiResponse<Library>> deleteLibraryById(int libraryId);
	
	public ResponseEntity<ApiResponse<Library>> updateLibraryPartially(LibraryDto dto, int libraryId);

	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(int libraryId, int BookId);

	public ResponseEntity<ApiResponse<List<Book>>> displayBookFromLibrary(int libraryId);
}

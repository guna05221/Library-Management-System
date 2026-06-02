package com.lms.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.LibraryDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.exception.BookIdNotFoundException;
import com.lms.exception.LibraryIdNotFoundException;
import com.lms.exception.LibraryNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.BookRepository;
import com.lms.repository.LibraryRepository;
import com.lms.service.ILibraryService;
import com.lms.until.ApiResponse;

@Service
public class LibraryService implements ILibraryService {

	@Autowired
	LibraryRepository libraryRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiResponse<Library>> saveLibrary(LibraryDto libraryDto, int addressId) {
		// convert dto to entity
		Library library = modelMapper.map(libraryDto, Library.class);

		// fetch address
		Optional<Address> optionalAddress = addressRepository.findById(addressId);

		ApiResponse<Library> apiResponse = new ApiResponse<>();

		if (optionalAddress.isPresent()) {
			// set address to library
			Address address = optionalAddress.get();
			library.setAddress(address);

			// save the library
			libraryRepository.save(library);

			apiResponse.setMessage("Library Saved Successfully.");
			apiResponse.setData(library);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
			return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.OK);
		}
		
		throw new LibraryIdNotFoundException("Invalid Library Id!!!");
	}

	@Override
	public ResponseEntity<ApiResponse<Library>> updateLibrary(LibraryDto libraryDto) {

		Library library = modelMapper.map(libraryDto, Library.class);

		libraryRepository.save(library);

		ApiResponse<Library> apiResponse = new ApiResponse<>();

		apiResponse.setMessage("Library Updated Successfully.");
		apiResponse.setData(library);
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
		return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ApiResponse<Library>> findLibraryById(int libraryId) {

		ApiResponse<Library> apiResponse = new ApiResponse<>();

		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);

		if (optionalLibrary.isPresent()) {
			Library library = optionalLibrary.get();

			apiResponse.setMessage("Library Found Successfully.");
			apiResponse.setData(library);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
			return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.OK);
		} else {
			throw new LibraryIdNotFoundException("Invalid Library Id!!!");
		}

	}

	@Override
	public ResponseEntity<ApiResponse<List<Library>>> findAllLibrary() {

		ApiResponse<List<Library>> apiResponse = new ApiResponse<>();

		List<Library> libraryList = libraryRepository.findAll();

		if (libraryList != null && !libraryList.isEmpty()) {
			apiResponse.setMessage("Library Found Successfully.");
			apiResponse.setData(libraryList);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
			return new ResponseEntity<ApiResponse<List<Library>>>(apiResponse, HttpStatus.OK);
		} else {
			throw new LibraryNotFoundException("Library DB Empty!!!");

		}

	}

	@Override
	public ResponseEntity<ApiResponse<Library>> deleteLibraryById(int libraryId) {
		
		ApiResponse<Library> apiResponse = new ApiResponse<>();
		
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
	
		if(optionalLibrary.isPresent()) {
			Library library = optionalLibrary.get();
		
			library.setAddress(null);
			libraryRepository.delete(library);
			
			apiResponse.setMessage("Library Deleted Successfully.");
			apiResponse.setData(null);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
					
			return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.OK);
		} else {
			throw new LibraryIdNotFoundException("Invalid Library Id!!!");

		}
	}
	
	public ResponseEntity<ApiResponse<Library>> updateLibraryPartially(LibraryDto libraryDto, int libraryId){
		Optional<Library> optional = libraryRepository.findById(libraryId);
		
		ApiResponse<Library> apiResponse = new ApiResponse<>();
		
		if(optional.isPresent()) {
			Library library = optional.get();
			
			library.setLibraryName(libraryDto.getLibraryName());
			
			libraryRepository.save(library);
			
			apiResponse.setMessage("Library Updated Successfully.");
			apiResponse.setData(library);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
					
			return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.OK);
		}else {
			throw new LibraryIdNotFoundException("Invalid Library Id!!!");
		}
	}
	
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(int libraryId, int BookId){
		// fetch both objects using their ids
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		Optional<Book> optionalBook = bookRepository.findById(BookId);
		ApiResponse<Library> apiResponse = new ApiResponse<>();
		
		if(optionalLibrary.isPresent() && optionalBook.isPresent()) {
		// fetch entity object from optional
		Library library = optionalLibrary.get();
		Book book = optionalBook.get();
		
		//fetch list of book from library
		
		List<Book> bookList = library.getBookList();
		
		if(bookList==null) {
			bookList = new ArrayList<>();
		}
		// add book object to the list
		
		bookList.add(book);
		
		library.setBookList(bookList);
		
		libraryRepository.save(library);
		
		apiResponse.setMessage("Book Added to Library Successfully.");
		apiResponse.setData(library);
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
				
		return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.OK);
		}else {
			throw new BookIdNotFoundException("Library Id/ Book Id not available");
		}
	}
	
	public ResponseEntity<ApiResponse<List<Book>>> displayBookFromLibrary(int libraryId){
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		ApiResponse<List<Book>> apiResponse = new ApiResponse<>();
		if(optionalLibrary.isPresent()) {
			Library library = optionalLibrary.get();
			List<Book> bookList = library.getBookList();
			
			if(bookList!=null && !bookList.isEmpty()) {
				apiResponse.setMessage("Book List Fetched Successfully");
				apiResponse.setData(bookList);
				apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
						
				return new ResponseEntity<ApiResponse<List<Book>>>(apiResponse,HttpStatus.OK);
			}
		}
			throw new LibraryNotFoundException("Library Not Found!!!");
	
	}
}

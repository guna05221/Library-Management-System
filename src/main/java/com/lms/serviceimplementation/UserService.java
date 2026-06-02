package com.lms.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.UserDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.exception.BookUnableToBorrowException;
import com.lms.exception.BookUnableToReturnException;
import com.lms.exception.UserIdNotFoundException;
import com.lms.exception.UserNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.BookRepository;
import com.lms.repository.UserRepository;
import com.lms.service.IUserService;
import com.lms.until.ApiResponse;

@Service
public class UserService implements IUserService{

	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	ModelMapper modelMapper;

	
	@Override
	public ResponseEntity<ApiResponse<User>> saveUser(UserDto userDto, int addressId) {
		// convert dto to entity
		User user = modelMapper.map(userDto, User.class);

		// fetch address
		Optional<Address> optionalAddress = addressRepository.findById(addressId);

		ApiResponse<User> apiResponse = new ApiResponse<>();

		if (optionalAddress.isPresent()) {
			// set address to user
			Address address = optionalAddress.get();
			user.setAddress(address);

			// save the user
			userRepository.save(user);

			apiResponse.setMessage("User Saved Successfully.");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		} else {
			throw new UserIdNotFoundException("Invalid User Id!!!");

		}
	}

//	@Override
//	public ResponseEntity<ApiResponse<User>> updateUser(UserDto userDto) {
//
//		User user = modelMapper.map(userDto, User.class);
//
//		userRepository.save(user);
//
//		ApiResponse<User> apiResponse = new ApiResponse<>();
//
//		apiResponse.setMessage("User Updated Successfully.");
//		apiResponse.setData(user);
//		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
//		return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
//
//	}

	@Override
	public ResponseEntity<ApiResponse<User>> findUserById(int userId) {

		ApiResponse<User> apiResponse = new ApiResponse<>();

		Optional<User> optionalUser = userRepository.findById(userId);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			apiResponse.setMessage("User Found Successfully.");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		} else {
			throw new UserIdNotFoundException("Invalid User Id!!!");
		}

	}

	@Override
	public ResponseEntity<ApiResponse<List<User>>> findAllUser() {

		ApiResponse<List<User>> apiResponse = new ApiResponse<>();

		List<User> userList = userRepository.findAll();

		if (userList != null && !userList.isEmpty()) {
			apiResponse.setMessage("User Found Successfully.");
			apiResponse.setData(userList);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
			return new ResponseEntity<ApiResponse<List<User>>>(apiResponse, HttpStatus.OK);
		} else {
			throw new UserNotFoundException("User DB Empty!!!");

		}

	}

	@Override
	public ResponseEntity<ApiResponse<User>> deleteUserById(int userId) {
		
		ApiResponse<User> apiResponse = new ApiResponse<>();
		
		Optional<User> optionalUser = userRepository.findById(userId);
	
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
		
			user.setAddress(null);
			userRepository.delete(user);
			
			apiResponse.setMessage("User Deleted Successfully.");
			apiResponse.setData(null);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
					
			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		} else {
			throw new UserIdNotFoundException("Invalid User Id!!!");
		}
	}
	
	/*
	 * complete update : entire object including id-@PutMapping 
	 * partial update : 	particular field : @PatchMapping
	 */

	public ResponseEntity<ApiResponse<User>> updateUserComplete(UserDto UserDto){
		User user = modelMapper.map(UserDto, User.class);
		userRepository.save(user);
		
		ApiResponse<User> apiResponse = new ApiResponse<>();
		
		apiResponse.setMessage("User Updated Successfully.");
		apiResponse.setData(user);
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
				
		return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		
	}
	
	
	
	public ResponseEntity<ApiResponse<User>> updateUserPartially(UserDto UserDto, int userId){
		// using id fetch the old user object
		Optional<User> optional = userRepository.findById(userId);
		
		ApiResponse<User> apiResponse = new ApiResponse<>();
		
		if(optional.isPresent()) {
			// separate user from optional 
			User user = optional.get();
			
			// we are updating the name 
			// for old user set the new name 
			
			user.setUserName(UserDto.getUserName());
			
			// now update the old user	
			userRepository.save(user);
		
			
			apiResponse.setMessage("User Updated Successfully.");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
					
			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		}else {
			throw new UserIdNotFoundException("Invalid Id!!!"); 
		}
	
	}
	
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(int userId, int BookId){
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Book> optionalBook = bookRepository.findById(BookId);
		ApiResponse<User> apiResponse = new ApiResponse<>();
		
		if(optionalUser.isPresent() && optionalBook.isPresent() && !optionalBook.get().isBorrowed()) {
			User user = optionalUser.get();
			Book book = optionalBook.get();
			
			//set book to user
			book.setUser(user);
			book.setBorrowedTime(LocalDateTime.now());
			book.setReturnTime(null);
			book.setBorrowed(true);
			
			//update Book
			bookRepository.save(book);
			
			apiResponse.setMessage("User Borrowed book Successfully.");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		}else {
			throw new BookUnableToBorrowException("Invalid Book Id/ User Id or Book is already Borrowed.");
		}
	}
	
	public ResponseEntity<ApiResponse<User>> returnBookByUser(int userId, int BookId){
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Book> optionalBook = bookRepository.findById(BookId);
		ApiResponse<User> apiResponse = new ApiResponse<>();
		
		if(optionalUser.isPresent() && optionalBook.isPresent() && optionalBook.get().isBorrowed()) {
			User user = optionalUser.get();
			Book book = optionalBook.get();
			
			book.setUser(null);
			book.setReturnTime(LocalDateTime.now());
			book.setBorrowed(false);
			
			bookRepository.save(book);
			
			apiResponse.setMessage("User Return book Successfully.");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		}else {
			throw new BookUnableToReturnException("User Return Already/ Not Borrowed.");
		}
	}
}

package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.until.ApiResponse;

public interface IUserService {

	public ResponseEntity<ApiResponse<User>> saveUser(UserDto userDto, int addressId);

//	public ResponseEntity<ApiResponse<User>> updateUser(UserDto userDto);

	public ResponseEntity<ApiResponse<User>> findUserById(int userId);

	public ResponseEntity<ApiResponse<List<User>>> findAllUser();

	public ResponseEntity<ApiResponse<User>> deleteUserById(int userId);
	
	public ResponseEntity<ApiResponse<User>> updateUserComplete(UserDto UserDto);
	
	public ResponseEntity<ApiResponse<User>> updateUserPartially(UserDto UserDto, int userId);

	public ResponseEntity<ApiResponse<User>> borrowBookByUser(int userId, int BookId);

	public ResponseEntity<ApiResponse<User>> returnBookByUser(int userId, int BookId);
}

package com.lms.until;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

	private int statusCode;
	public String message;
	public T data;
}

/*
 * datatype of data should be generic 
 * 
 * eg:
 *  ArrayList<Employee>
 *
 *	ApiResponse<Address>
 *		Address data
 *
 *Change status code of browser(postman) use response entity - response entity is an inbuilt class
 *
 */

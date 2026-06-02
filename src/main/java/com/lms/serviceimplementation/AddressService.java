package com.lms.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.AddressDto;
import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.entity.Address;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.exception.AddressNotFoundException;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.service.IAddressService;
import com.lms.until.ApiResponse;

@Service
public class AddressService implements IAddressService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AddressRepository addressRepository;

	@Override
	public ResponseEntity<ApiResponse<Address>> saveAddress(AddressDto addressDto) {
		Address address = modelMapper.map(addressDto, Address.class);
		addressRepository.save(address);

		// set response
		ApiResponse<Address> apiResponse = new ApiResponse<>();
		apiResponse.setMessage("Address Saved Successfully.");
		apiResponse.setData(address);
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
		return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<Address>> findAddressById(int addressId) {

		Optional<Address> optional = addressRepository.findById(addressId);
		ApiResponse<Address> apiResponse = new ApiResponse<>();
		if (optional.isPresent()) {
			Address address = optional.get();

			apiResponse.setMessage("Address Found Successfully by : " + address.getAddressId() + ".");
			apiResponse.setData(address);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => OK
			return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);

		} else {

//			apiResponse.setMessage("Address Not Found!!!");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400 => BAD_REQUEST
//			return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.BAD_REQUEST);

			throw new AddressIdNotFoundException("Invalid Addrerss Id!!!");
			
		}
	}

	@Override
	public ResponseEntity<ApiResponse<Address>> updateAddress(AddressDto addressDto) {

		Address address = modelMapper.map(addressDto, Address.class);
		addressRepository.save(address);
		ApiResponse<Address> apiResponse = new ApiResponse<>();

		apiResponse.setMessage("Address Updated Successfully.");
		apiResponse.setData(address);
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => OK
		return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<Address>> deleteAddressById(int addressId) {
		Optional<Address> optional = addressRepository.findById(addressId);
		ApiResponse<Address> apiResponse = new ApiResponse<>();

		if (optional.isPresent()) {
			Address address = optional.get();
			addressRepository.delete(address);
			apiResponse.setMessage("Address Deleted Successfully.");
			apiResponse.setData(address);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => OK
			return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
		} else {
			throw new AddressIdNotFoundException("Invalid Addrerss Id!!!");
		}

	}

	@Override
	public ResponseEntity<ApiResponse<List<Address>>> findAllAddress() {
		List<Address> addressList = addressRepository.findAll();
		ApiResponse<List<Address>> apiResponse = new ApiResponse<>();
		if (addressList != null && !addressList.isEmpty()) {
			apiResponse.setMessage("Addresses Found Successfully.");
			apiResponse.setData(addressList);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<List<Address>>>(apiResponse, HttpStatus.OK);
		}
		throw new AddressNotFoundException("Empty DataBase!!!");
	}
	
	public ResponseEntity<ApiResponse<Address>> updateAddressPartially(AddressDto addressDto, int addressId){
		Optional<Address> optional = addressRepository.findById(addressId);
		
		ApiResponse<Address> apiResponse = new ApiResponse<>();
		
		if(optional.isPresent()) {
			Address address = optional.get();
			
			address.setArea(addressDto.getArea());
			
			addressRepository.save(address);
			
			apiResponse.setMessage("Address Updated Successfully.");
			apiResponse.setData(address);
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 => ok
					
			return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
		}else {
			throw new AddressIdNotFoundException("Invalid Address Id!!!");
		}
	}

}

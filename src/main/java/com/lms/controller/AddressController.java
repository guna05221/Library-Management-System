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

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.service.IAddressService;
import com.lms.until.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/*
 * @Operation : used to specify what operation that particular Api(method) does
 * @ApiResponse : used to specify what response that particular api gives
 */

@RestController
// common url for entier class
@RequestMapping("/address")
public class AddressController {

	@Autowired
	IAddressService addressService;
	
	
	  @Operation(
		        operationId = "CreateAddress",
		        summary = "Adding one address",
		        description = "This REST endpoint is used to create and add one address"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Address created and returned successfully"
		        )
		    })
	@PostMapping
	public ResponseEntity<ApiResponse<Address>> saveAddress(@RequestBody AddressDto addressDto) {
		return addressService.saveAddress(addressDto);
	}
	
	  @Operation(
		        operationId = "FetchAddressById",
		        summary = "Fetching one address",
		        description = "This REST endpoint is used to fetch address using addressId"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Address fetched successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid address id"
		        )
		    })
	@GetMapping("/{addressId}")
	public ResponseEntity<ApiResponse<Address>> findAddressById(@PathVariable int addressId) {
		return addressService.findAddressById(addressId);
	}
	
	  @Operation(
		        operationId = "UpdateAddress",
		        summary = "Updating one address",
		        description = "This REST endpoint is used to update address details"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Address updated successfully"
		        )
		    })
	@PutMapping
	public ResponseEntity<ApiResponse<Address>> updateAddress(@RequestBody AddressDto addressDto) {
		return addressService.updateAddress(addressDto);
	}
	
	  @Operation(
		        operationId = "DeleteAddress",
		        summary = "Deleting one address",
		        description = "This REST endpoint is used to delete address using addressId"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Address deleted successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid address id"
		        )
		    })
	@DeleteMapping("/{addressId}")
	public ResponseEntity<ApiResponse<Address>> deleteAddressById(@PathVariable int addressId){
		return addressService.deleteAddressById(addressId);
	}
	
	  @Operation(
		        operationId = "FetchAllAddresses",
		        summary = "Fetching all addresses",
		        description = "This REST endpoint is used to fetch all addresses"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "All addresses fetched successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Database is empty"
		        )
		    })
	@GetMapping
	public ResponseEntity<ApiResponse<List<Address>>> findAllAddress() {
		return addressService.findAllAddress();
	}
	
	  @Operation(
		        operationId = "PartialUpdateAddress",
		        summary = "Partially updating address",
		        description = "This REST endpoint is used to partially update address details"
		    )
		    @ApiResponses(value = {
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "200",
		            description = "Address partially updated successfully"
		        ),
		        @io.swagger.v3.oas.annotations.responses.ApiResponse(
		            responseCode = "400",
		            description = "Invalid address id"
		        )
		    })
	@PatchMapping("/{addressId}")
	public ResponseEntity<ApiResponse<Address>> updateAddressPartially(@RequestBody AddressDto dto,@PathVariable int addressId){
		return addressService.updateAddressPartially(dto, addressId);
	}
	
	
}

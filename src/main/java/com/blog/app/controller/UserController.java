package com.blog.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.UserDto;
import com.blog.app.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	//POST -- create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		 UserDto createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	}
	
	
	 //for dummy purpose 
//	@GetMapping("/data")
//	public String getMethodName() {
//		return "Hello Ajay";
//	}
	
	//PUT -- update user
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Integer uId, @RequestBody UserDto userDto) {
		
		UserDto updatedUser=this.userService.updateUserById(userDto, uId);
		 return ResponseEntity.ok(updatedUser);
		
	}
	
	//DELETE -- delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "delete/{id}", produces = "application/json")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer uId){
		this.userService.deleteUserById(uId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully...",true),HttpStatus.OK);
		
	}
	
	
	//GET -- get all users
	
	@GetMapping("/data")
	public List<UserDto> getAllUsers() {
		
		List<UserDto> users=this.userService.getAllUsers();
		return users;
	}
	
	
	//GET -- get user by id
	
		@GetMapping("/{id}")
		public UserDto getUserById(@PathVariable Integer id) {
			
			UserDto user=this.userService.getUserById(id);
			return user;
		}
	
	
}

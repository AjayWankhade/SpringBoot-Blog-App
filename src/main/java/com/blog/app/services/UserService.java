package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.User;
import com.blog.app.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto userDto);

	UserDto createUser(UserDto userDto);

	UserDto getUserById(Integer id);

	UserDto updateUserById(UserDto userDto, Integer id);

	List<UserDto> getAllUsers();

	void deleteUserById(Integer id);
}

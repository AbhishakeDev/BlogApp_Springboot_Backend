package com.blog.services;

import com.blog.entities.User;
import com.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}

package com.blogging_apis.blog.service;

import com.blogging_apis.blog.payloads.UserDto;

import java.util.List;

public interface  UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer id);
}

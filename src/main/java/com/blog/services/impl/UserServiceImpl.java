package com.blog.services.impl;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User", " id ", userId)));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        return this.userToDto(this.userRepo.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User", " id ", userId))));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map(user -> this.userToDto(user)).collect((Collectors.toList()));
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "Id", userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto) {
        User user=new User();
        user.setId(userDto.getId());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
//        User user = this.modelMapper.map(userDto,User.class);
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAbout(user.getAbout());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());

//        UserDto userDto this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}

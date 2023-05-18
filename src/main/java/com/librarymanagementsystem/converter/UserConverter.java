package com.librarymanagementsystem.converter;

import com.librarymanagementsystem.dto.UserDTO;
import com.librarymanagementsystem.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;


    public UserDTO entityToDto(User users)
    {
        return modelMapper.map(users,UserDTO.class);
    }

    public List<UserDTO> entityToDto(List<User> users)
    {
        return users.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public User dtoToEntity(UserDTO usersDTO)
    {
        return modelMapper.map(usersDTO,User.class);
    }
}

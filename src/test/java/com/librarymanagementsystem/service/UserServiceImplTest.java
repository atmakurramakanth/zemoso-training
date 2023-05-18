package com.librarymanagementsystem.service;

import com.librarymanagementsystem.converter.UserConverter;
import com.librarymanagementsystem.dao.UserRepository;
import com.librarymanagementsystem.dto.UserDTO;
import com.librarymanagementsystem.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @MockBean
    UserRepository userRepository;

    @Autowired
    Services<User> usersService;

    @Autowired
    UserConverter usersConverter;

    @Test
    public void saveUser() {
        User user =  new User(100,"rakesh","student","test");
        when(userRepository.save(user)).thenReturn(user);
        usersService.save(user);
        verify(userRepository,times(1)).save(user);
    }


    @Test
    public void findById() {
        User user = new User(100,"rakesh","student","test");
        when(userRepository.findById(100)).thenReturn(Optional.of(user));
        Assertions.assertEquals(user,usersService.findById(100));
    }

    @Test
    public void updateUser() {
        User user = new User(100,"rakesh","student","test");
        when(userRepository.findById(100)).thenReturn(Optional.of(user));
        User modifyingUser = usersService.findById(100);
        modifyingUser.setPassword("test@123");
        usersService.save(modifyingUser);
        Assertions.assertEquals(modifyingUser,usersService.findById(100));
    }


    @Test
    public void deleteUser() {
        User user =  new User(100,"rakesh","student","test");
        usersService.deleteById(100);
        verify(userRepository,times(1)).deleteById(user.getId());
    }

    @Test
    public void getUserList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1,"coke","student","coca cola");
        User user2 = new User(2,"kurkure","student","haldirams");
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll() ).thenReturn(userList);
        Assertions.assertEquals(2,usersService.findAll().size());
    }

    @Test
    public void checkingUsersDTO()
    {
        UserDTO finalUserDTO = new UserDTO();
        UserDTO usersDTO = new UserDTO(100,"rakesh","student","test");
        finalUserDTO.setId(usersDTO.getId());
        finalUserDTO.setUserName(usersDTO.getUserName());
        finalUserDTO.setPassword(usersDTO.getPassword());
        finalUserDTO.setUserRole(usersDTO.getUserRole());
        Assertions.assertEquals("test",finalUserDTO.getPassword());
    }

    @Test
    public void testingUserConverterEntityToDTO()
    {
        User user1 = new User(100,"rakesh","student","test");
        User user2 = new User(101,"mukesh","student","test");
        UserDTO usersDTO1 = usersConverter.entityToDto(user1);
        List<UserDTO> usersDTOList = usersConverter.entityToDto(Stream.of(user1,user2).collect(Collectors.toList()));
        User user3 = usersConverter.dtoToEntity(usersDTO1);
        Assertions.assertEquals(2,usersDTOList.size());
        Assertions.assertEquals("rakesh",usersDTO1.getUserName());
        Assertions.assertEquals("rakesh",user3.getUserName());
    }
}

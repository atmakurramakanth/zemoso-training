package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dao.UserRepository;
import com.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.exceptionhandler.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements Services<User>{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
            Optional<User> result=userRepository.findById(id);
            User user=null;
            if(result.isPresent()) {
                user=result.get();
            }else{
                throw new NotFoundException("User with id : "+id+" not found.");
            }
        return user;
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

}

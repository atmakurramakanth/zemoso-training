package com.librarymanagementsystem.controller;

import com.librarymanagementsystem.converter.UserConverter;
import com.librarymanagementsystem.dto.UserDTO;
import com.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.exceptionhandler.WrongPasswordException;
import com.librarymanagementsystem.service.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    static Services<User> service;
    static boolean error=false;
    static User user = null;

    @Autowired
    private static UserConverter userConverter;

    @Autowired
    public UserController(Services<User> userService) {
        loadService(userService);
    }

    private static void loadService(Services<User> userService) {
        service=userService;
    }

    @GetMapping("/")
    public String showLoginForm(Model model){
        User newUser=new User();
        newUser.setId(999);
        model.addAttribute("user",newUser);
        model.addAttribute("error",error);
        return "index";
    }

    @PostMapping("/login")
    public static String validate(@Valid @ModelAttribute("user") UserDTO postUserDTO, BindingResult bindingResult, Model model) {
        try {
            User dbUser =service.findById(postUserDTO.getId());

            if (dbUser.getPassword().equals(postUserDTO.getPassword())) {
                error=false;
                user=dbUser;
                if(dbUser.getUserRole().equals("librarian")){
                    return "redirect:/showlibbooks";
                }else{
                    return "redirect:/showbooks";
                }
            }
            throw new WrongPasswordException("Wrong Password");
        }catch (Exception e){
            error=true;
        }

        return "redirect:/";
    }



}

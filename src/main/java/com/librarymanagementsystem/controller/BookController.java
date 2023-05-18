package com.librarymanagementsystem.controller;

import com.librarymanagementsystem.converter.BookConverter;
import com.librarymanagementsystem.converter.UserConverter;
import com.librarymanagementsystem.dto.BookDTO;
import com.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    private Services<Book> service;

    @Autowired
    private BookConverter bookConverter;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    public BookController(Services<Book> service) {
        this.service = service;
    }

    @GetMapping("/showlibbooks")
    public String showLibraryBooks(Model model){
        model.addAttribute("user",userConverter.entityToDto(UserController.user));
        model.addAttribute("books",bookConverter.entityToDto(service.findAll()));
        return  "show-lib-books";
    }

    @GetMapping("showbooks")
    public String showBooks(Model model){
        model.addAttribute("user",userConverter.entityToDto(UserController.user));
        model.addAttribute("books",bookConverter.entityToDto(service.findAll()));
        return "show-books";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Book book=new Book();
        model.addAttribute("book",bookConverter.entityToDto(book));
        return "books/book-form";
    }

    @PostMapping("/books/save")
    public String saveEmployee(@ModelAttribute("book") BookDTO bookDTO){
        service.save(bookConverter.dtoToEntity(bookDTO));
        return "redirect:/showlibbooks";
    }

    @GetMapping("/books/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int id, Model model)  {
        Book book=service.findById(id);
        model.addAttribute("book",bookConverter.entityToDto(book));
        return "books/book-form";
    }

    @GetMapping("/books/delete")
    public String delete(@RequestParam("bookId") int id){
        service.deleteById(id);
        return "redirect:/showlibbooks";
    }

    public void setBookConverter(BookConverter bookConverter) {
        this.bookConverter = bookConverter;
    }

    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }
}

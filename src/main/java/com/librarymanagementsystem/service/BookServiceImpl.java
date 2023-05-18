package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dao.BookRepository;
import com.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.exceptionhandler.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements Services<Book> {
    private BookRepository bookRepository;
    
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        Optional<Book> result=bookRepository.findById(id);
        Book book=null;
        if(result.isPresent()) {
            book=result.get();
        }else{
            throw new NotFoundException("Book with id : "+id+" not found.");
        }
        return book;
    }


    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

}

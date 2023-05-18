package com.librarymanagementsystem.converter;

import com.librarymanagementsystem.dto.BookDTO;
import com.librarymanagementsystem.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BookDTO entityToDto(Book book)
    {
        return modelMapper.map(book,BookDTO.class);
    }

    public List<BookDTO> entityToDto(List<Book> books)
    {
        return books.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Book dtoToEntity(BookDTO bookDTO)
    {
        return modelMapper.map(bookDTO,Book.class);
    }
}

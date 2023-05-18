package com.librarymanagementsystem.service;

import com.librarymanagementsystem.converter.BookConverter;
import com.librarymanagementsystem.dao.BookRepository;
import com.librarymanagementsystem.dto.BookDTO;
import com.librarymanagementsystem.entity.Book;
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
public class BookServiceImplTest {

    @MockBean
    BookRepository bookRepository;

    @Autowired
    Services<Book> bookServices;

    @Autowired
    BookConverter bookConverter;

    @Test
    public void saveBook() {
        Book book=new Book(100,"Temp Book","Rakesh","this is a temp book","100$");
        when(bookRepository.save(book)).thenReturn(book);
        bookServices.save(book);
        verify(bookRepository,times(1)).save(book);
    }


    @Test
    public void findById() {
        Book book=new Book(100,"Temp Book","Rakesh","this is a temp book","100$");
        when(bookRepository.findById(100)).thenReturn(Optional.of(book));
        Assertions.assertEquals(book,bookServices.findById(100));
    }

    @Test
    public void updateBook() {
        Book book=new Book(100,"Temp Book","Rakesh","this is a temp book","100$");
        when(bookRepository.findById(100)).thenReturn(Optional.of(book));
        Book modifyingBook = bookServices.findById(100);
        modifyingBook.setAuthorName("Mukesh");
        bookServices.save(modifyingBook);
        Assertions.assertEquals(modifyingBook,bookServices.findById(100));
    }


    @Test
    public void deleteBook() {
        Book book=new Book(100,"Temp Book","Rakesh","this is a temp book","100$");
        bookServices.deleteById(100);
        verify(bookRepository,times(1)).deleteById(book.getId());
    }

    @Test
    public void getBookList() {
        List<Book> bookList = new ArrayList<>();
        Book book1=new Book(100,"Temp Book 1","Rakesh","this is a temp book 1","100$");
        Book book2=new Book(101,"Temp Book 2","Mukesh","this is a temp book 2","200$");
        bookList.add(book1);
        bookList.add(book2);
        when(bookRepository.findAll() ).thenReturn(bookList);
        Assertions.assertEquals(2,bookServices.findAll().size());
    }

    @Test
    public void checkingBookDTO()
    {
        BookDTO finalBookDTO = new BookDTO();
        BookDTO bookDTO = new BookDTO(100,"Temp Book 1","Rakesh","this is a temp book 1","100$");
        finalBookDTO.setId(bookDTO.getId());
        finalBookDTO.setBookName(bookDTO.getBookName());
        finalBookDTO.setAuthorName(bookDTO.getAuthorName());
        finalBookDTO.setDescription(bookDTO.getDescription());
        Assertions.assertEquals("Rakesh",finalBookDTO.getAuthorName());
    }

    @Test
    public void testingBookConverterEntityToDTO()
    {
        Book book1=new Book(100,"Temp Book 1","rakesh","this is a temp book 1","100$");
        Book book2=new Book(101,"Temp Book 2","Mukesh","this is a temp book 2","200$");
        BookDTO bookDTO = bookConverter.entityToDto(book1);
        List<BookDTO> bookDTOList = bookConverter.entityToDto(Stream.of(book1,book2).collect(Collectors.toList()));
        Book book3 = bookConverter.dtoToEntity(bookDTO);
        Assertions.assertEquals(2,bookDTOList.size());
        Assertions.assertEquals("rakesh",bookDTO.getAuthorName());
        Assertions.assertEquals("rakesh",book3.getAuthorName());
    }
}

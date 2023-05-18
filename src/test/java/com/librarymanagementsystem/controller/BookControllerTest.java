package com.librarymanagementsystem.controller;


import com.librarymanagementsystem.converter.BookConverter;
import com.librarymanagementsystem.converter.UserConverter;
import com.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.service.Services;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

    private MockMvc mockMvc;

    BookController bookController;

    @Autowired
    Services<Book> bookServices;

    @Autowired
    UserConverter userConverter;

    @Autowired
    BookConverter bookConverter;

    @Before
    public void setup()
    {
        bookController = new BookController(bookServices);
        bookController.setBookConverter(bookConverter);
        bookController.setUserConverter(userConverter);
        mockMvc= MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void contextLoads()
    {
        setup();
        Assertions.assertThat(bookController).isNotNull();
    }

    @Test
    public void showLibraryBooksTest() throws Exception {
        setup();
        UserController.user=new User(100,"rakesh","student","test");
        mockMvc.perform(MockMvcRequestBuilders.get("/showlibbooks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show-lib-books"));
    }

    @Test
    public void showFormForAddTest() throws Exception {
        setup();
        UserController.user=new User(100,"rakesh","student","test");
        mockMvc.perform(MockMvcRequestBuilders.get("/showFormForAdd"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("books/book-form"));
    }

    @Test
    public void showFormForUpdateTest() throws Exception {
        setup();
        UserController.user=new User(100,"rakesh","student","test");
        mockMvc.perform(MockMvcRequestBuilders.get("/books/showFormForUpdate?bookId=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("books/book-form"));
    }

    @Test
    public void showBooksTest() throws Exception {
        setup();
        UserController.user=new User(100,"rakesh","student","test");
        mockMvc.perform(MockMvcRequestBuilders.get("/showbooks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show-books"));
    }


    @Test
    public void saveEmployeeTest() throws Exception {
        setup();
         mockMvc.perform(post("/books/save")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("id", String.valueOf(100)).
                        param("bookName","Temp Book")
                        .param("authorName","Rakesh")
                        .param("description","this is a temp book")
                        .param("price","100$")
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/showlibbooks"));
    }

//    @Test
//    public void deleteBookTest() throws Exception {
//        setup();
//        mockMvc.perform(get("/books/delete?bookId=13"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(view().name("redirect:/showlibbooks"));
//    }

}

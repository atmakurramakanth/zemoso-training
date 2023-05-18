package com.librarymanagementsystem.controller;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    UserController userController;

    @Autowired
    Services<User> userServices;

    @Before
    public void setup()
    {
        userController = new UserController(userServices);
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void contextLoads()
    {
        setup();
        Assertions.assertThat(userController).isNotNull();
    }

    @Test
    public void shouldReturnIndexView() throws Exception {
        setup();
       mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void returnHomePageForLibrarian() throws Exception {
        setup();
        mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .param("id", String.valueOf(300)).param("password","test@123")
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/showlibbooks"));
    }

    @Test
    public void returnHomePageForUser() throws Exception {
        setup();
        mockMvc.perform(post("/login")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("id", String.valueOf(146)).param("password","test@123")
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/showbooks"));
    }

    @Test
    public void returnInvalidPage() throws Exception {
        setup();
        mockMvc.perform(post("/login"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

}


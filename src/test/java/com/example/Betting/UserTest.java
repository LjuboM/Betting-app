package com.example.Betting;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Betting.controller.UserController;
import com.example.Betting.model.User;
import com.example.Betting.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserTest {
 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private UserService userService;
 
    @Test
    public void givenUserId_whenGetUsers_thenReturnIsOkAndJson()
      throws Exception {
         
    	User ljubo = new User(1, "Ljubo Mamic", "Split", 24, 500, null);

        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljubo));

        mvc.perform(MockMvcRequestBuilders.get("/api/user/{id}", 1)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("{\r\n" + 
          		"    \"id\": 1,\r\n" + 
          		"    \"name\": \"Ljubo Mamic\",\r\n" + 
          		"    \"location\": \"Split\",\r\n" + 
          		"    \"age\": 24,\r\n" + 
          		"    \"money\": 500.0\r\n" + 
          		"}"));
    }
    
    @Test
    public void givenUserId_whenGetUsers_thenReturnIsBadRequest()
      throws Exception {

        given(userService.findUserById((long) 2)).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get("/api/user/{id}", 2)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
}
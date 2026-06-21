package com.assignment.requip.controller;

import com.assignment.requip.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Test
    void shouldReturnPersons() throws Exception {

        when(personService.getAllPersons(anyInt(), anyInt(), anyString()))
                .thenReturn(Page.empty());

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());

        verify(personService)
                .getAllPersons(0, 10, "createdAt");
    }
}
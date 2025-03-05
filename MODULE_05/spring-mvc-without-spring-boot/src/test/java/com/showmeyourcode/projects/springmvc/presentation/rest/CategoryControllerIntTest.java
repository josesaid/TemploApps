package com.showmeyourcode.projects.springmvc.presentation.rest;

import com.showmeyourcode.projects.springmvc.ITBase;
import com.showmeyourcode.projects.springmvc.domain.model.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerIntTest extends ITBase {

    @Test
    void shouldReturnAllCategoriesWithEvents() throws Exception {
        String jsonResponse = this.mockMvc.perform(get(CategoryController.CONTROLLER_PATH + "?withEvents=true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AtomicInteger categoriesWithEvents = new AtomicInteger();
        Arrays.asList(objectMapper.readValue(jsonResponse, CategoryDto[].class)).forEach(c -> {
            if (c.getEvents().size() > 0) {
                categoriesWithEvents.incrementAndGet();
            }
        });
        assertThat(categoriesWithEvents.get(), greaterThan(0));
    }

    @Test
    void shouldReturnAllCategoriesWithoutEvents() throws Exception {
        String jsonResponse = this.mockMvc.perform(get(CategoryController.CONTROLLER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Arrays.asList(objectMapper.readValue(jsonResponse, CategoryDto[].class)).forEach(c -> {
            assertThat(c.getEvents(), hasSize(0));
        });
    }
}

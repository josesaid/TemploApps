package com.showmeyourcode.projects.springmvc.presentation.rest;

import com.showmeyourcode.projects.springmvc.ITBase;
import com.showmeyourcode.projects.springmvc.domain.model.EventDto;
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

class EventControllerIntTest extends ITBase {

    @Test
    void shouldReturnAllAvailableEvents() throws Exception {
        String jsonResponse = this.mockMvc.perform(get(EventController.CONTROLLER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Arrays.asList(objectMapper.readValue(jsonResponse, EventDto[].class)).forEach(c -> {
            assertThat(c.getUsers(), hasSize(0));
        });
    }

    @Test
    void shouldReturnAllEventsWithUsers() throws Exception {
        String jsonResponse = this.mockMvc.perform(get(EventController.CONTROLLER_PATH + "?available=false&withUsers=true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AtomicInteger eventsWithUsers = new AtomicInteger();
        Arrays.asList(objectMapper.readValue(jsonResponse, EventDto[].class)).forEach(c -> {
            if (c.getUsers().size() > 0) {
                eventsWithUsers.incrementAndGet();
            }
        });
        assertThat(eventsWithUsers.get(), greaterThan(0));
    }
}

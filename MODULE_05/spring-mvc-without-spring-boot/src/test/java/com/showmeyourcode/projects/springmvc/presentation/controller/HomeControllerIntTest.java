package com.showmeyourcode.projects.springmvc.presentation.controller;

import com.showmeyourcode.projects.springmvc.ITBase;
import com.showmeyourcode.projects.springmvc.domain.model.CategoryDto;
import com.showmeyourcode.projects.springmvc.domain.model.EventDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class HomeControllerIntTest extends ITBase {

    /**
     * You cannot write assertions for the content of a JSP page
     * because JSP pages are rendered by a servlet container
     * and Spring MVC Test doesn't run a servlet container.
     * You can only verify that the view name is correct
     * and/or the request is forwarded to the correct url
     */
    @Test
    void shouldReturnDefaultViewForAnonymousUser() throws Exception {
        MvcResult response = this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home/index"))
                .andExpect(model().attributeExists("categories", "events"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<CategoryDto> categories = (List<CategoryDto>) response.getModelAndView().getModel().get("categories");
        List<EventDto> events = (List<EventDto>) response.getModelAndView().getModel().get("events");

        assertFalse(categories.isEmpty());
        assertFalse(events.isEmpty());
    }
}

package com.showmeyourcode.projects.springmvc.presentation.controller;

import com.showmeyourcode.projects.springmvc.ITBase;
import com.showmeyourcode.projects.springmvc.domain.model.EventDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerIntTest extends ITBase {

    @Test
    void shouldDisplayThePageToAuthorizedUser() throws Exception {
        MvcResult result = this.mockMvc.perform(get(UserController.CONTROLLER_PATH)
                .header(HttpHeaders.AUTHORIZATION, getValidCredentialsForUserAsHeader())
        ).andExpect(status().isOk()).andReturn();

        var viewAttributes = result.getModelAndView().getModel();
        assertThat(((Collection<EventDto>) viewAttributes.get("EventsCreated")).size(), Matchers.greaterThanOrEqualTo(2));
        assertThat(((Collection<EventDto>) viewAttributes.get("EventsParticipated")).size(), Matchers.greaterThanOrEqualTo(2));
    }

    @Test
    void shouldNotDisplayThePageToAuthorizedUser() throws Exception {
        this.mockMvc.perform(get(UserController.CONTROLLER_PATH)
                .header(HttpHeaders.AUTHORIZATION, getInvalidCredentialsForUserAsHeader())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRedirectWhenUserIsNotLoggedIn() throws Exception {
        this.mockMvc.perform(get(UserController.CONTROLLER_PATH))
                .andExpect(status().is3xxRedirection());
    }
}


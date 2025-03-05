package com.showmeyourcode.projects.springmvc.presentation.controller;

import com.showmeyourcode.projects.springmvc.ITBase;
import com.showmeyourcode.projects.springmvc.domain.model.RegisterLoginFormDto;
import com.showmeyourcode.projects.springmvc.infrastructure.entity.AppUserEntity;
import com.showmeyourcode.projects.springmvc.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.Instant;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class AccountControllerIntTest extends ITBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldDisplayLoginPage() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(AccountController.CONTROLLER_PATH + AccountController.LOGIN_PATH)
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void shouldDisplayRegisterPage() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(AccountController.CONTROLLER_PATH + AccountController.REGISTER_PATH)
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void shouldLogOutIfNoCredentialsArePresent() throws Exception {
        this.mockMvc.perform(get(UserController.CONTROLLER_PATH + UserController.LOGOUT_PATH))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void shouldLogOutWhenCredentialsArePresent() throws Exception {
        this.mockMvc.perform(get(UserController.CONTROLLER_PATH + UserController.LOGOUT_PATH)
                .header(HttpHeaders.AUTHORIZATION, getValidCredentialsForUserAsHeader())
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    void shouldNotLogInWhenPasswordIsInvalid() throws Exception {
        this.mockMvc.perform(get(AccountController.CONTROLLER_PATH + AccountController.SUCCESS_PATH)
                .header(HttpHeaders.AUTHORIZATION, getInvalidCredentialsForUserAsHeader())
        ).andExpect(status().is4xxClientError());
    }

    @Test
    void shouldNotLogInWhenUsernameIsInvalid() throws Exception {
        this.mockMvc.perform(get(AccountController.CONTROLLER_PATH + AccountController.SUCCESS_PATH)
                .header(HttpHeaders.AUTHORIZATION, getInvalidCredentialsForRandomUserAsHeader())
        ).andExpect(status().is4xxClientError());
    }

    @Test
    void shouldRedirectLoggedUserBasedOnRole() throws Exception {
        this.mockMvc.perform(get(AccountController.CONTROLLER_PATH + AccountController.SUCCESS_PATH)
                        .header(HttpHeaders.AUTHORIZATION, getValidCredentialsForUserAsHeader())
                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user"));

        this.mockMvc.perform(get(AccountController.CONTROLLER_PATH + AccountController.SUCCESS_PATH)
                        .header(HttpHeaders.AUTHORIZATION, getValidCredentialsForAdminAsHeader())
                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user"));
    }

    @Test
    void shouldRegisterNewUser() throws Exception {
        String nick = "nick12345";
        assertNull(userRepository.getByLogin(nick));

        RegisterLoginFormDto registerLoginForm = new RegisterLoginFormDto(
                new AppUserEntity(null,
                        "example@mail.com",
                        "password",
                        nick,
                        false,
                        Timestamp.from(Instant.now()),
                        "password"),
                null);
        this.mockMvc.perform(
                MockMvcRequestBuilders.multipart(AccountController.CONTROLLER_PATH + AccountController.REGISTER_PATH)
                        .flashAttr("form", registerLoginForm)
        ).andExpect(status().isOk()).andReturn();

        assertThat(userRepository.getByLogin(nick), is(notNullValue()));
    }
}

package com.showmeyourcode.projects.springmvc.presentation.controller;

import com.showmeyourcode.projects.springmvc.application.UserControllerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(value = UserController.CONTROLLER_PATH)
public class UserController {

    public static final String CONTROLLER_PATH = "/user";
    public static final String LOGOUT_PATH = "/logout";

    private final UserControllerService service;

    @GetMapping
    public String account(Model model) {
        return service.getAccountView(model);
    }

    @GetMapping(value = LOGOUT_PATH)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        return service.getLogoutView(request, response);
    }
}

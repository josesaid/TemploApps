package com.showmeyourcode.projects.springmvc.presentation.controller;

import com.showmeyourcode.projects.springmvc.application.AccountControllerService;
import com.showmeyourcode.projects.springmvc.domain.model.RegisterLoginFormDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(value = AccountController.CONTROLLER_PATH)
public class AccountController {
/*
    //WAR:
//    Servlet Container > app
    * Tomcat
        Jetty
        Netty
    wildFly
    App Server:
    JBoss
    BeaLogic WebLogic
    IBM WebSphere App server
    OAS
  */

    public static final String CONTROLLER_PATH = "/account";
    public static final String LOGIN_PATH = "/login";
    public static final String REGISTER_PATH = "/register";
    public static final String SUCCESS_PATH = "/success";

    private final AccountControllerService accountService;

    @GetMapping(value = SUCCESS_PATH)
    public String chooseViewToRedirect() {
        return "redirect:/user";
    }

    @GetMapping(value = LOGIN_PATH)
    public String login(Model model) {
        return accountService.getLoginAndRegisterView(model);
    }

    @PostMapping(value = REGISTER_PATH)
    public String register(Model model,
                           @ModelAttribute("form") RegisterLoginFormDto form,
                           BindingResult errorsResult) {
        //log.info("POST register");
        return accountService.registerNewUser(model, form, errorsResult);
    }

    @GetMapping(value = REGISTER_PATH)
    public String register(Model model) {
        return accountService.getLoginAndRegisterView(model);
    }
}


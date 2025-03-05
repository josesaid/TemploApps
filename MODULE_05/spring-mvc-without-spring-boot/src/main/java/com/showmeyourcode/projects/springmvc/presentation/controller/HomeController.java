package com.showmeyourcode.projects.springmvc.presentation.controller;

import com.showmeyourcode.projects.springmvc.application.HomeControllerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = HomeController.CONTROLLER_PATH)
@AllArgsConstructor
public class HomeController {

    public static final String CONTROLLER_PATH = "/";

    private final HomeControllerService homeService;

    @GetMapping
    public String index(Model model) {
        return homeService.returnHomeView(model);
    }
}

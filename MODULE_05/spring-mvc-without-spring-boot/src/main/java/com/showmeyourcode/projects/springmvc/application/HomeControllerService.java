package com.showmeyourcode.projects.springmvc.application;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Objects;

@Service
@AllArgsConstructor
public class HomeControllerService {

    private final CategoryService categoryService;
    private final EventService eventService;

    public String returnHomeView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = Objects.isNull(auth) || "anonymousUser".equals(auth.getName()) ? null : auth.getName();
        String role = Objects.nonNull(auth) && !auth.getAuthorities().isEmpty() ? auth.getAuthorities().toArray()[0].toString() : "";

        model.addAttribute("categories", categoryService.getByCount(4, false));
        model.addAttribute("events", eventService.getByCount(6));
        model.addAttribute("UserName", login);
        model.addAttribute("Role", role);
        return "home/index";
    }
}

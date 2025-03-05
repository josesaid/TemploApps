package com.showmeyourcode.projects.springmvc.application;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserControllerService {

    private final DefaultUserDetailsService accountService;
    private final CategoryService categoryService;
    private final EventService eventService;

    public String getAccountView(Model model) {
        String login = getUsername();
        model.addAttribute("UserName", login);
        model.addAttribute("EventsCreated", eventService.getCreatedByUser(accountService.userId(login)));
        model.addAttribute("EventsParticipated", eventService.getParticipatedByUser(accountService.userId(login)));
        model.addAttribute("Categories", categoryService.getAll(false));
        model.addAttribute("Role", getUserRole());
        return "user/account";
    }

    public String getLogoutView(HttpServletRequest request, HttpServletResponse response) {
        logout(request, response);
        return "redirect:/login";
    }

    private String getUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(auth) && !auth.getAuthorities().isEmpty() ? auth.getAuthorities().toArray()[0].toString() : "";
    }

    private String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(auth) ? auth.getName() : null;
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
}

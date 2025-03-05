package com.showmeyourcode.projects.springmvc.application;

import com.showmeyourcode.projects.springmvc.domain.model.RegisterLoginFormDto;
import com.showmeyourcode.projects.springmvc.domain.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Service
@AllArgsConstructor
public class AccountControllerService {

    private final DefaultUserDetailsService accountService;

    public String getLoginAndRegisterView(Model model) {
        model.addAttribute("form", new RegisterLoginFormDto());
        return "login";
    }

    public String registerNewUser(
            Model model,
            RegisterLoginFormDto form,
            BindingResult result
    ) {
        UserValidator validator = new UserValidator(accountService.isLoginAlreadyExists(form.getUser().getNick()));
        validator.validate(form.getUser(), result);
        UserValidator.bindErrors(result, form.getUser());
        if (result.hasErrors()) {
            return "login";
        }

        model.addAttribute("registerSucced", true);
        model.addAttribute("form", new RegisterLoginFormDto());
        accountService.register(form.getUser());
        return "login";
    }
}

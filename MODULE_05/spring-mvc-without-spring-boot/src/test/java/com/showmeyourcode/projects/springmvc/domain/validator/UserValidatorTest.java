package com.showmeyourcode.projects.springmvc.domain.validator;

import com.showmeyourcode.projects.springmvc.domain.model.RegisterLoginFormDto;
import com.showmeyourcode.projects.springmvc.infrastructure.entity.AppUserEntity;
import com.showmeyourcode.projects.springmvc.infrastructure.entity.CategoryEntity;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.hamcrest.MatcherAssert.assertThat;

class UserValidatorTest {

    private UserValidator userValidator;
    private RegisterLoginFormDto formValidUser;
    private RegisterLoginFormDto formInvalidUser;
    private AppUserEntity validUser;
    private AppUserEntity invalidUser;

    @BeforeEach
    public void setup() {
        validUser = AppUserEntity.builder()
                .nick("ExampleNick")
                .email("sample@mail.mail.com")
                .password("jkg123kduqhk2")
                .confirmPassword("jkg123kduqhk2")
                .build();
        invalidUser = AppUserEntity.builder()
                .nick("!@$#%^&*^%$#%^&^*")
                .email("")
                .password("")
                .confirmPassword("234")
                .build();
        formValidUser = RegisterLoginFormDto.builder()
                .user(validUser)
                .build();
        formInvalidUser = RegisterLoginFormDto.builder()
                .user(validUser)
                .build();
    }

    @Test
    void shouldDetectIfANickIsInUse() {
        userValidator = new UserValidator(false);
        Errors errors = new BeanPropertyBindingResult(formValidUser, "form");
        userValidator.validate(validUser, errors);

        assertThat(errors.getFieldError("user.nick"), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    void shouldDetectWhenPasswordsMismatch() {
        userValidator = new UserValidator(true);
        validUser.setConfirmPassword("fdfsdf");
        Errors errors = new BeanPropertyBindingResult(formValidUser, "form");
        userValidator.validate(validUser, errors);

        assertThat(errors.getFieldError("user.confirmPassword"), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    void shouldSupportUserEntityOnly() {
        userValidator = new UserValidator(false);
        assertThat(userValidator.supports(AppUserEntity.class), CoreMatchers.is(true));
        assertThat(userValidator.supports(CategoryEntity.class), CoreMatchers.is(false));
    }

    @Test
    void shouldValidateCorrectUserData() {
        userValidator = new UserValidator(true);
        Errors errors = new BeanPropertyBindingResult(formValidUser, "form");
        userValidator.validate(validUser, errors);

        assertThat(errors.hasErrors(), CoreMatchers.is(false));
    }

    @Test
    void shouldValidateIncorrectUserData() {
        userValidator = new UserValidator(true);
        Errors errors = new BeanPropertyBindingResult(formInvalidUser, "form");
        userValidator.validate(invalidUser, errors);

        assertThat(errors.hasErrors(), CoreMatchers.is(true));
    }
}

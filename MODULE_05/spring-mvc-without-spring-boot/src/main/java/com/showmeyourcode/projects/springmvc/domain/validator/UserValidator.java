package com.showmeyourcode.projects.springmvc.domain.validator;

import com.showmeyourcode.projects.springmvc.infrastructure.entity.AppUserEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.ArrayList;

@AllArgsConstructor
public class UserValidator implements Validator {

    private final boolean isUnique;

    public static void bindErrors(BindingResult errorsResult, AppUserEntity user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        jakarta.validation.Validator validator = factory.getValidator();
        ArrayList<ConstraintViolation<AppUserEntity>> errors = new ArrayList(validator.validate(user));
        for (ConstraintViolation<AppUserEntity> constraint : errors)
            errorsResult.addError(new FieldError("form", "user." + constraint.getPropertyPath().toString(), constraint.getMessage()));
    }

    public boolean supports(Class<?> paramClass) {
        return AppUserEntity.class.equals(paramClass);
    }

    public void validate(Object obj, Errors errors) {
        AppUserEntity user = (AppUserEntity) obj;
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("user.confirmPassword", "account.passconf");
        }
        if (!isUnique) {
            errors.rejectValue("user.nick", "account.login");
        }
    }
}

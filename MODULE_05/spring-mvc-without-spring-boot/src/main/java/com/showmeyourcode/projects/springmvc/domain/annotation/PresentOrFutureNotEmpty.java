package com.showmeyourcode.projects.springmvc.domain.annotation;

import com.showmeyourcode.projects.springmvc.domain.validator.PresentOrFutureDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PresentOrFutureDateValidator.class)
@Documented
public @interface PresentOrFutureNotEmpty {
    Class<?>[] groups() default {};

    String message() default "{PresentOrFuture.message}";

    Class<? extends Payload>[] payload() default {};
}

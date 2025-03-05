package com.showmeyourcode.projects.springmvc.domain.validator;

import com.showmeyourcode.projects.springmvc.domain.annotation.PresentOrFutureNotEmpty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class PresentOrFutureDateValidator implements ConstraintValidator<PresentOrFutureNotEmpty, Date> {

    private String message;

    public boolean isValid(final Date date, final ConstraintValidatorContext context) {
        if (Objects.isNull(date)) {
            message = "{newEvent.date.empty}";
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }
        return date.after(new Date(Instant.now().toEpochMilli()));
    }
}

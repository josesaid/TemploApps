package com.showmeyourcode.projects.springmvc.domain.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PresentOrFutureDateValidatorTest {

    private PresentOrFutureDateValidator dateValidator;
    private ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    public void setup() {
        dateValidator = new PresentOrFutureDateValidator();
        constraintValidatorContext = mock(ConstraintValidatorContext.class);
    }

    @Test
    void shouldSuccessfullyValidateCorrectDates() {
        assertThat(
                dateValidator.isValid(new Date(Instant.now().plusSeconds(10_000L).toEpochMilli()), constraintValidatorContext),
                CoreMatchers.is(true)
        );
    }

    @Test
    void shouldValidateIncorrectDates() {
        assertThat(
                dateValidator.isValid(new Date(Instant.now().minus(20, ChronoUnit.DAYS).toEpochMilli()), constraintValidatorContext),
                CoreMatchers.is(false)
        );
    }

    @Test
    void shouldValidateNullData() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));

        assertThat(
                dateValidator.isValid(null, constraintValidatorContext),
                CoreMatchers.is(false)
        );
        verify(constraintValidatorContext, times(1)).buildConstraintViolationWithTemplate(any());
    }
}

package com.template.demo.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.template.demo.exception.BusinessException;

public class RucValidatorTest {
    private final RucValidator validator = new RucValidator();

    @Test
    void shouldValidateRucSuccessfully() {

        assertDoesNotThrow(() -> validator.validate("12345678901"));
    }

    @Test
    void shouldThrowExceptionWhenRucLengthInvalid() {

        assertThrows(
                BusinessException.class,
                () -> validator.validate("123"));
    }

    @Test
    void shouldThrowExceptionWhenRucContainsLetters() {

        assertThrows(
                BusinessException.class,
                () -> validator.validate("12345ABCDE1"));
    }
}

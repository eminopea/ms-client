package com.template.demo.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.template.demo.exception.BusinessException;


// TDD: primero escribes las pruebas, luego implementas el código hasta que las pruebas pasen.
// Junit5: se ha utilizado solo JUnit5, ya que el validador no tiene 
// dependencias externas que requieran el uso de Mockito u otro framework de mocking.

public class DniValidatorTest {
    private final DniValidator validator = new DniValidator();

    @Test
    void shouldValidateDniSuccessfully() {

        assertDoesNotThrow(() -> validator.validate("12345678"));
    }

    @Test
    void shouldThrowExceptionWhenDniLengthInvalid() {

        assertThrows(
                BusinessException.class,
                () -> validator.validate("123"));
    }

    @Test
    void shouldThrowExceptionWhenDniContainsLetters() {

        assertThrows(
                BusinessException.class,
                () -> validator.validate("1234ABCD"));
    }
}
package com.krzpro.validatorapi.core.validator.luhn;

import com.krzpro.validatorapi.core.model.ValidationResult;
import com.krzpro.validatorapi.core.validator.LuhnValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LuhnValidatorTest {

    private final LuhnValidator validator = new LuhnValidator();

    @Test
    void validLuhn_shouldPass_andNormalize() {
        ValidationResult<LuhnValidator.LuhnDetails> result =
                validator.validate("3782 8224 6310 005");

        assertTrue(result.isValid());
        assertEquals("378282246310005", result.getNormalized());
        assertNull(result.getReason());
        assertNotNull(result.getDetails());
        assertEquals(15, result.getDetails().length());
    }

    @Test
    void invalidLength_shouldFail() {
        ValidationResult<LuhnValidator.LuhnDetails> result =
                validator.validate("12345678901234567890");

        assertFalse(result.isValid());
        assertEquals("INVALID_LENGTH", result.getReason());
    }

    @Test
    void invalidFormat_shouldFail() {
        ValidationResult<LuhnValidator.LuhnDetails> result =
                validator.validate("378A2822463B10005");

        assertFalse(result.isValid());
        assertEquals("INVALID_FORMAT", result.getReason());
    }

    @Test
    void invalidChecksum_shouldFail() {
        ValidationResult<LuhnValidator.LuhnDetails> result =
                validator.validate("378282246310 123");

        assertFalse(result.isValid());
        assertEquals("INVALID_CHECKSUM", result.getReason());
    }

}

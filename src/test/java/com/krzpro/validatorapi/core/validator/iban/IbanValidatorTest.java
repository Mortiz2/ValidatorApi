package com.krzpro.validatorapi.core.validator.iban;

import com.krzpro.validatorapi.core.model.ValidationResult;
import com.krzpro.validatorapi.core.validator.IbanValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class IbanValidatorTest {

    private final IbanValidator validator = new IbanValidator();

    @Test
    void validIban_shouldPass_andNormalize() {
        ValidationResult<IbanValidator.IbanDetails> result =
                validator.validate("PL61 1090 1014 0000 0712 1981 2874");

        assertTrue(result.isValid());
        assertEquals("PL61109010140000071219812874", result.getNormalized());
        assertNull(result.getReason());
        assertNotNull(result.getDetails());
        assertEquals("PL", result.getDetails().countryCode());
    }

    @Test
    void invalidChecksum_shouldFail() {
        ValidationResult<IbanValidator.IbanDetails> result =
                validator.validate("PL00109010140000071219812874");

        assertFalse(result.isValid());
        assertEquals("INVALID_CHECKSUM", result.getReason());
    }

    @Test
    void invalidFormat_shouldFail() {
        ValidationResult<IbanValidator.IbanDetails> result =
                validator.validate("not-an-iban");

        assertFalse(result.isValid());
        assertEquals("INVALID_FORMAT", result.getReason());
    }

    @Test
    void tooShortInput_shouldFailLength() {
        ValidationResult<IbanValidator.IbanDetails> result =
                validator.validate("PL611090101400");

        assertFalse(result.isValid());
        assertEquals("INVALID_LENGTH", result.getReason());
    }
}
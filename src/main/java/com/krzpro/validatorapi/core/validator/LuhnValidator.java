package com.krzpro.validatorapi.core.validator;

import com.krzpro.validatorapi.core.model.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class LuhnValidator {

    public record LuhnDetails(int length) {}

    public ValidationResult<LuhnDetails> validate(String raw) {
        if (raw == null) {
            return ValidationResult.fail(null, "NULL_INPUT", null);
        }

        String normalized = normalize(raw);

        if (normalized.isEmpty()) {
            return ValidationResult.fail(normalized, "INVALID_LENGTH", null);
        }

        if (!normalized.matches("^[0-9]+$")) {
            return ValidationResult.fail(normalized, "INVALID_FORMAT", null);
        }

        if (normalized.length() < 2 || normalized.length() > 19) {
            return ValidationResult.fail(normalized, "INVALID_LENGTH", new LuhnDetails(normalized.length()));
        }

        if (!passesLuhn(normalized)) {
            return ValidationResult.fail(normalized, "INVALID_CHECKSUM", new LuhnDetails(normalized.length()));
        }

        return ValidationResult.ok(normalized, new LuhnDetails(normalized.length()));
    }

    private String normalize(String raw) {
        return raw.replaceAll("[\\s-+]+", "");
    }

    private boolean passesLuhn(String digits) {
        int sum = 0;
        boolean doubleDigit = false;

        for (int i = digits.length() -1; i >= 0; i--) {
            int d = digits.charAt(i) - '0';

            if (doubleDigit) {
                d = d*2;
                if (d > 9) {
                    d = d-9;
                }
            }

            sum += d;
            doubleDigit = !doubleDigit;
        }

        return (sum % 10) == 0;
    }
}

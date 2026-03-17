package com.krzpro.validatorapi.core.validator;

import com.krzpro.validatorapi.core.model.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class IbanValidator {

    public record IbanDetails(String countryCode, String checkDigits) {}

    public ValidationResult<IbanDetails> validate(String raw) {
        if (raw == null) {
            return ValidationResult.fail(null, "NULL_INPUT", null);
        }

        String normalized = normalize(raw);

        if (normalized.length() < 15) {
            if (!looksLikeIbanPrefix(normalized)) {
                return ValidationResult.fail(normalized, "INVALID_FORMAT", null);
            }
            return ValidationResult.fail(normalized, "INVALID_LENGTH", null);
        }

        if (!normalized.matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{11,30}$")) {
            return ValidationResult.fail(normalized, "INVALID_FORMAT", null);
        }

        String countryCode = normalized.substring(0, 2);
        String checkDigits = normalized.substring(2, 4);

        boolean checksumOk = mod97Check(normalized);
        if (!checksumOk) {
            return ValidationResult.fail(normalized, "INVALID_CHECKSUM", new IbanDetails(countryCode, checkDigits));
        }

        return ValidationResult.ok(normalized, new IbanDetails(countryCode, checkDigits));
    }

    private boolean looksLikeIbanPrefix(String value) {
        return value.matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]*$");
    }

    private String normalize(String raw) {
        String noSpaces = raw.replaceAll("\\s+", "");
        return noSpaces.toUpperCase(Locale.ROOT);
    }

    private boolean mod97Check(String iban) {
        String rearranged = iban.substring(4) + iban.substring(0, 4);

        int mod = 0;
        for (int i = 0; i < rearranged.length(); i++) {
            char c = rearranged.charAt(i);

            if (c >= '0' && c <= '9') {
                mod = (mod * 10 + (c - '0')) % 97;
            } else if (c >= 'A' && c <= 'Z') {
                int val = 10 + (c - 'A');
                mod = (mod * 10 + (val / 10)) % 97;
                mod = (mod * 10 + (val % 10)) % 97;
            } else {
                return false;
            }
        }

        return mod == 1;
    }
}

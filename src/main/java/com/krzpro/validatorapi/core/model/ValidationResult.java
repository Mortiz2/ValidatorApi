package com.krzpro.validatorapi.core.model;

public class ValidationResult<T> {
    private final boolean valid;
    private final String normalized;
    private final String reason;
    private final T details;

    public ValidationResult(boolean valid, String normalized, String reason, T details) {
        this.valid = valid;
        this.normalized = normalized;
        this.reason = reason;
        this.details = details;
    }

    public static <T> ValidationResult<T> ok(String normalized, T details) {
        return new ValidationResult<>(true, normalized, null, details);
    }

    public static <T> ValidationResult<T> fail(String normalized, String reason,T details) {
        return new ValidationResult<>(false, normalized, reason, details);
    }

    public boolean isValid() {
        return valid;
    }

    public String getNormalized() {
        return normalized;
    }

    public String getReason() {
        return reason;
    }

    public T getDetails() {
        return details;
    }
}

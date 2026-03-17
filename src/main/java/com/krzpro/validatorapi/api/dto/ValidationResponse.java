package com.krzpro.validatorapi.api.dto;

public class ValidationResponse<T> {
    private boolean valid;
    private String normalized;
    private String reason;
    private T details;

    public ValidationResponse() {}

    public ValidationResponse(boolean valid, String normalized, String reason, T details) {
        this.valid = valid;
        this.normalized = normalized;
        this.reason = reason;
        this.details = details;
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

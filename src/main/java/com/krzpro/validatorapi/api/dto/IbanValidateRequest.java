package com.krzpro.validatorapi.api.dto;

import jakarta.validation.constraints.NotBlank;

public class IbanValidateRequest {

    @NotBlank
    private String iban;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}

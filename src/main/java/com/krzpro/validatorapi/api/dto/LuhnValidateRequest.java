package com.krzpro.validatorapi.api.dto;

import jakarta.validation.constraints.NotBlank;

public class LuhnValidateRequest {

    @NotBlank
    private String luhn;

    public String getLuhn() {
        return luhn;
    }

    public void setLuhn(String luhn) {
        this.luhn = luhn;
    }
}

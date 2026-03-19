package com.krzpro.validatorapi.core.service;

import com.krzpro.validatorapi.core.model.ValidationResult;
import com.krzpro.validatorapi.core.validator.LuhnValidator;
import org.springframework.stereotype.Service;

@Service
public class LuhnValidationService {

    private final LuhnValidator luhnValidator;

    public LuhnValidationService(LuhnValidator luhnValidator) {
        this.luhnValidator = luhnValidator;
    }

    public ValidationResult<LuhnValidator.LuhnDetails> validate(String luhn) {
        return luhnValidator.validate(luhn);
    }
}
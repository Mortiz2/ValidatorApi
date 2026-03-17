package com.krzpro.validatorapi.core.service;

import com.krzpro.validatorapi.core.model.ValidationResult;
import com.krzpro.validatorapi.core.validator.IbanValidator;
import org.springframework.stereotype.Service;

@Service
public class IbanValidationService {

    private final IbanValidator ibanValidator;

    public IbanValidationService(IbanValidator ibanValidator) {
        this.ibanValidator = ibanValidator;
    }

    public ValidationResult<IbanValidator.IbanDetails> validate(String iban) {
        return ibanValidator.validate(iban);
    }
}

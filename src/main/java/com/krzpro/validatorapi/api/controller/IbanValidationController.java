package com.krzpro.validatorapi.api.controller;

import com.krzpro.validatorapi.api.dto.ValidationResponse;
import com.krzpro.validatorapi.api.dto.IbanValidateRequest;
import com.krzpro.validatorapi.core.model.ValidationResult;
import com.krzpro.validatorapi.core.service.IbanValidationService;
import com.krzpro.validatorapi.core.validator.IbanValidator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/validate")
public class IbanValidationController {

    private final IbanValidationService service;

    public IbanValidationController(IbanValidationService service) {
        this.service = service;
    }

    @PostMapping("/iban")
    public ValidationResponse<IbanValidator.IbanDetails> validate(@Valid @RequestBody IbanValidateRequest request) {
        ValidationResult<IbanValidator.IbanDetails> result = service.validate(request.getIban());
        return new ValidationResponse<>(
                result.isValid(),
                result.getNormalized(),
                result.getReason(),
                result.getDetails()
        );
    }
}

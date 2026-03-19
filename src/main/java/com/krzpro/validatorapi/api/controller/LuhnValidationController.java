package com.krzpro.validatorapi.api.controller;

import com.krzpro.validatorapi.api.dto.LuhnValidateRequest;
import com.krzpro.validatorapi.api.dto.ValidationResponse;
import com.krzpro.validatorapi.core.model.ValidationResult;
import com.krzpro.validatorapi.core.service.LuhnValidationService;
import com.krzpro.validatorapi.core.validator.LuhnValidator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/validate")
public class LuhnValidationController {

    private final LuhnValidationService service;

    public LuhnValidationController(LuhnValidationService service) {
        this.service = service;
    }

    @PostMapping("/luhn")
    public ValidationResponse<LuhnValidator.LuhnDetails> validate(@Valid @RequestBody LuhnValidateRequest request) {
        ValidationResult<LuhnValidator.LuhnDetails> result = service.validate(request.getLuhn());
        return new ValidationResponse<>(
                result.isValid(),
                result.getNormalized(),
                result.getReason(),
                result.getDetails()
        );
    }
}
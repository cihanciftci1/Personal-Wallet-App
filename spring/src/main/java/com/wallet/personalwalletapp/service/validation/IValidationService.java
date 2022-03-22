package com.wallet.personalwalletapp.service.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IValidationService {
    public ResponseEntity<?> validate(BindingResult result);
}

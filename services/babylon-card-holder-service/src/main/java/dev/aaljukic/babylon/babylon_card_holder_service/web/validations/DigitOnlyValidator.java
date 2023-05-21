package dev.aaljukic.babylon.babylon_card_holder_service.web.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DigitOnlyValidator implements ConstraintValidator<DigitOnly, String> {
    public void initialize(DigitOnly constraint) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("\\d+");
    }
}

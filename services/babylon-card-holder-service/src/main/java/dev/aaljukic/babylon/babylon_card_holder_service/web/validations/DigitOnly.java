package dev.aaljukic.babylon.babylon_card_holder_service.web.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DigitOnlyValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DigitOnly {
    String message() default "The field should contain only digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

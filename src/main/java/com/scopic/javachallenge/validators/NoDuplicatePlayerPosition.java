package com.scopic.javachallenge.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoDuplicatePlayerPositionImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@ReportAsSingleViolation
public @interface NoDuplicatePlayerPosition {
    String message() default "The position of the player should not be repeated in the request.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

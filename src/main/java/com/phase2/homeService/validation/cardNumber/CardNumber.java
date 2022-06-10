package com.phase2.homeService.validation.cardNumber;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CardNumberConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNumber {
    String message() default "must contain jtp";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package com.phase2.homeService.validation.cvv2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Cvv2ConstraintValidator implements ConstraintValidator<Cvv2, String> {
    @Override
    public void initialize(Cvv2 constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cvv2, ConstraintValidatorContext constraintValidatorContext) {
        for (Character ch : cvv2.toCharArray()) {
            if (!Character.isDigit(ch))
                System.out.println("Cvv2 must be just number!!!");
        }

        if (cvv2.length() != 4)
            System.out.println("Invalid card length number");

        return true;
    }
}

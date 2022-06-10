package com.phase2.homeService.validation.secondPassword;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecondPasswordConstraintValue implements ConstraintValidator<SecondPassword,String> {
    @Override
    public void initialize(SecondPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length() != 8)
            System.out.println("Card Number must have 16 number!!!");

        for (Character ch:s.toCharArray()) {
            if(!Character.isDigit(ch))
                System.out.println("Second password must be just number!!!");
        }
        return true;
    }
}

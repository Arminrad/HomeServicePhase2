package com.phase2.homeService.validation.cardNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardNumberConstraintValidator implements ConstraintValidator<CardNumber,String> {
    @Override
    public void initialize(CardNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length() != 16)
            System.out.println("Card Number must have 16 number!!!");

        for (Character ch:s.toCharArray()) {
            if(!Character.isDigit(ch))
                System.out.println("Invalid card number");;
        }
        return true;
    }
}

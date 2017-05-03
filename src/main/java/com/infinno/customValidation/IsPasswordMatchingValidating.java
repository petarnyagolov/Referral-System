package com.infinno.customValidation;

import com.infinno.models.bindingModels.RegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidating implements ConstraintValidator<IsPasswordMatching,Object>{

    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if (userClass instanceof RegistrationModel) {
            return ((RegistrationModel)userClass).getPassword().equals(((RegistrationModel) userClass).getConfirmPassword());
        }
        return false;
    }
}

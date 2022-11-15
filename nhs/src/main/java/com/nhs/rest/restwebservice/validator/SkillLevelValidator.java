package com.nhs.rest.restwebservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class SkillLevelValidator implements ConstraintValidator<SkillLevel, String> {
	
	 List<String> skillLevel = Arrays.asList("Expert", "Practitioner", "Working", "Awareness");

	    @Override
	    public boolean isValid(String value, ConstraintValidatorContext context) {

	        return skillLevel.contains(value);

	    }

}

package com.citadini.ourcity.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = InsertOccurrenceCommentValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface InsertOccurrenceComment {
	String message() default "Validation Error";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
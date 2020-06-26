package com.pay.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = RoomTokenValidator.class)
public @interface RoomToken {
    String[] value();

    String message() default "Amount or Number is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

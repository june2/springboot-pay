package com.pay.validator;

import com.pay.exception.BadRequestException;
import com.pay.handler.ResponseType;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class RoomTokenValidator implements ConstraintValidator<RoomToken, Object> {
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private String[] fields;

    @Override
    public void initialize(RoomToken constraintAnnotation) {
        fields = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            long amount = Long.parseLong(PARSER.parseExpression(fields[0]).getValue(value).toString());
            long number = Long.parseLong(PARSER.parseExpression(fields[1]).getValue(value).toString());
            if (amount == 0 || number == 0) return false;
            return amount % number == 0;
        } catch (Exception e) {
            throw new BadRequestException(ResponseType.BAD_REQUEST, "Invalid param");
        }
    }
}

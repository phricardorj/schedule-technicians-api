package br.com.phricardo.schedulingtechnicians.validation.cpf;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfValidator.class)
public @interface CPF {

    String message() default "invalid CPF";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

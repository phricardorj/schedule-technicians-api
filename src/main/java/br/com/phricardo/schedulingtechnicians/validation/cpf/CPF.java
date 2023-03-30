package br.com.phricardo.schedulingtechnicians.validation.cpf;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates that the annotated string is a valid Brazilian CPF (Cadastro de Pessoas Físicas).
 *
 * The CPF must be a 14 character string, with separators, because the periods and the hyphen are mandatory.
 *
 * Author: Pedro Henrique Ricardo (@phricardorj | phricardo.com.br)
 * Created: March 30, 2023
 *
 * @since 1.0
 * */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfValidator.class)
public @interface CPF {

    String message() default "invalid CPF";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

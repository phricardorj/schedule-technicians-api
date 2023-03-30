package br.com.phricardo.schedulingtechnicians.validation.cnpj;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates that the annotated string is a valid Brazilian CNPJ (Cadastro Nacional de Pessoa Jurídica).
 *
 * The CNPJ must be an 18 character string, with separators, because the dots, diagonal slash and hyphen are mandatory.
 *
 * Author: Pedro Henrique Ricardo (@phricardorj | phricardo.com.br)
 * Created: March 30, 2023
 *
 * @since 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CnpjValidator.class)
public @interface CNPJ {

    String message() default "invalid CNPJ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

package br.com.phricardo.schedulingtechnicians.validation.cnpj;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CnpjValidator.class)
public @interface CNPJ {

    String message() default "invalid CNPJ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

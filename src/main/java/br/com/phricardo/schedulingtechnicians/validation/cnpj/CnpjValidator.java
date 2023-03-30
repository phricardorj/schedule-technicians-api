package br.com.phricardo.schedulingtechnicians.validation.cnpj;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CnpjValidator implements ConstraintValidator<CNPJ, String> {

    @Override
    public void initialize(CNPJ constraintAnnotation) {}

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if(cnpj == null) return false;
        Matcher matcher = Pattern.compile("^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$").matcher(cnpj);
        if(!matcher.matches()) return false;
        cnpj = cnpj.replaceAll("[./-]", "");
        if(cnpj.length() != 14) return false;
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        // Calculate the verification digits
        int sum = 0;
        int weight = 2;
        for (int i = 11; i >= 0; i--) {
            sum += (cnpj.charAt(i) - '0') * weight;
            weight = (weight + 1) % 10 + (weight + 1) / 10 * 2;
        }

        int firstVerificationDigit = sum % 11 == 0 || sum % 11 == 1 ? 0 : 11 - sum % 11;

        sum = 0;
        weight = 2;
        for (int i = 12; i >= 0; i--) {
            sum += (cnpj.charAt(i) - '0') * weight;
            weight = (weight + 1) % 10 + (weight + 1) / 10 * 2;
        }

        int secondVerificationDigit = sum % 11 == 0 || sum % 11 == 1 ? 0 : 11 - sum % 11;

        // Verify that the verification digits match the last two digits of the CNPJ
        return cnpj.charAt(12) - '0' == firstVerificationDigit && cnpj.charAt(13) - '0' == secondVerificationDigit;
    }
}

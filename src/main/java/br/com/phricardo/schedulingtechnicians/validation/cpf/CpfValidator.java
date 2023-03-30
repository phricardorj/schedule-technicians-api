package br.com.phricardo.schedulingtechnicians.validation.cpf;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CpfValidator implements ConstraintValidator<CPF, String> {

    @Override
    public void initialize(CPF constraintAnnotation) {}

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if(cpf == null) return false;
        Matcher matcher = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$").matcher(cpf);
        if(!matcher.matches()) return false;
        cpf = cpf.replaceAll("[.-]", "");
        if(cpf.length() != 11) return false;

        // Check if all digits are equal
        if (cpf.matches("(\\d)\\1{10}")) return false;

        // Calculate the verification digits
        int sum = 0;
        for(int i = 0; i < 9; i++) {
            sum += (10 - i) * (cpf.charAt(i) - '0');
        }
        int firstVerificationDigit = sum % 11;
        if (firstVerificationDigit < 2) {
            firstVerificationDigit = 0;
        } else {
            firstVerificationDigit = 11 - firstVerificationDigit;
        }

        sum = 0;
        for(int i = 0; i < 10; i++) {
            sum += (11 - i) * (cpf.charAt(i) - '0');
        }
        int secondVerificationDigit = sum % 11;
        if (secondVerificationDigit < 2) {
            secondVerificationDigit = 0;
        } else {
            secondVerificationDigit = 11 - secondVerificationDigit;
        }

        // Verify that the verification digits match the last two digits of the CPF
        return cpf.charAt(9) - '0' == firstVerificationDigit && cpf.charAt(10) - '0' == secondVerificationDigit;
    }
}

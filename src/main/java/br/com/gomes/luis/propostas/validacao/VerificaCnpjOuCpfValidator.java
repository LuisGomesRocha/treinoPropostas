package br.com.gomes.luis.propostas.validacao;

import br.com.gomes.luis.propostas.utils.CpfCnpjUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerificaCnpjOuCpfValidator implements ConstraintValidator<CnpjOuCpf, String> {

    private String value;

    @Override
    public void initialize(CnpjOuCpf constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
       return CpfCnpjUtils.isValid(value);
    }
}
package br.com.gomes.luis.propostas.utils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.logging.Logger;

public class CpfCnpjUtils {
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static boolean isValid(String cpfCnpj) {
        return (isValidCPF(cpfCnpj) || isValidCNPJ(cpfCnpj));
    }

    public static String formaterDocumento(String documento) throws Exception {

        documento = documento.replaceAll("[^0-9]", "");

        if (isValidCPF(documento)) {
            return formatarCPF(documento);
        }

        if (isValidCNPJ(documento)) {

            return formatarCNPJ(documento);

            }

        return null;

    }

    private static String formatarCPF(String documento) throws ParseException {
        return aplicarMascara(documento,"###.###.###-##" );
    }

    private static String formatarCNPJ(String documento) throws ParseException {
       return aplicarMascara(documento,"###.###.###/####-##" );
    }

    private static String aplicarMascara(String documento,  String mascara) throws ParseException {
        MaskFormatter mask = new MaskFormatter(mascara);
        mask.setValueContainsLiteralCharacters(false);
        return mask.valueToString(documento);

    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    private static String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }

    private static boolean isValidCPF(String cpf) {
        cpf = cpf.trim().replace(".", "").replace("-", "");
        if ((cpf==null) || (cpf.length()!=11)) return false;

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    private static boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.trim().replace(".", "").replace("-", "");
        if ((cnpj==null)||(cnpj.length()!=14)) return false;

        Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    }

}

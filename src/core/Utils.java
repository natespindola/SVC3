package core;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
    public static boolean isValidEmailAddress(String email)
    {
        try {
        Class.forName("javax.mail.internet.AddressException");
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static boolean isValidCPF(String CPF)
    {
    // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (
            CPF.equals("00000000000") || CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))

            return(false);

            char dig10, dig11;
            int sm, i, r, num, peso;

            try {
            // Calculo do 1o. Digito Verificador
                sm = 0;
                peso = 10;
                for (i=0; i<9; i++) {
            // converte o i-esimo caractere do CPF em um numero:
            // por exemplo, transforma o caractere '0' no inteiro 0
            // (48 eh a posicao de '0' na tabela ASCII)
                    num = (int)(CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig10 = '0';
                else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

// Calculo do 2o. Digito Verificador
                sm = 0;
                peso = 11;
                for(i=0; i<10; i++) {
                    num = (int)(CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig11 = '0';
                else dig11 = (char)(r + 48);

// Verifica se os digitos calculados conferem com os digitos informados.
                if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                    return(true);
                else return(false);
            } catch (InputMismatchException erro) {
                return(false);
            }
        }

        public static String imprimeCPF(String CPF) {
            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                    CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }

        public static boolean isValidCNPJ(String CNPJ) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
            if (
                    CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
                    CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
                    CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
                    CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
                    CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
                    (CNPJ.length() != 14))
                return(false);

            char dig13, dig14;
            int sm, i, r, num, peso;

            try {
            // Calculo do 1o. Digito Verificador
                sm = 0;
                peso = 2;
                for (i=11; i>=0; i--) {
                    num = (int)(CNPJ.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso + 1;
                    if (peso == 10)
                        peso = 2;
                }

                r = sm % 11;
                if ((r == 0) || (r == 1))
                    dig13 = '0';
                else dig13 = (char)((11-r) + 48);

                sm = 0;
                peso = 2;
                for (i=12; i>=0; i--) {
                    num = (int)(CNPJ.charAt(i)- 48);
                    sm = sm + (num * peso);
                    peso = peso + 1;
                    if (peso == 10)
                        peso = 2;
                }

                r = sm % 11;
                if ((r == 0) || (r == 1))
                    dig14 = '0';
                else dig14 = (char)((11-r) + 48);

// Verifica se os dígitos calculados conferem com os dígitos informados.
                if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
                    return(true);
                else return(false);
            } catch (InputMismatchException erro) {
                return(false);
            }
        }

        public static String imprimeCNPJ(String CNPJ) {
// máscara do CNPJ: 99.999.999.9999-99
            return(CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
                    CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
                    CNPJ.substring(12, 14));
        }

        public static String encodeMD5(String text)
        {
            try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] bytesOfMessage = text.getBytes("UTF-8");
                byte[] thedigest = md.digest(bytesOfMessage);
                BigInteger bigInt = new BigInteger(1,thedigest);
                String hashtext = bigInt.toString(16);
                // Now we need to zero pad it if you actually want the full 32 chars.
                while(hashtext.length() < 32 ){
                    hashtext = "0"+hashtext;
                }
                return hashtext;
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        public static boolean matchExpression(String pattern, String text)
        {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(text);

            return m.matches();
        }
        public static String randomString()
        {
            final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&*";

            SecureRandom rnd = new SecureRandom();

            StringBuilder sb = new StringBuilder();
            for( int i = 0; i < 8; i++ ) {
                sb.append(AB.charAt(rnd.nextInt(AB.length())));
            }
            if(!matchExpression("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$",sb.toString())){
                for( int i = 0; i < 8; i++ ) {
                    sb.append(AB.charAt(rnd.nextInt(AB.length())));
                }
            }

            return sb.toString();
    }
}

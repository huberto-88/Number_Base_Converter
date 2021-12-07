package converter;

import java.math.BigInteger;

public class Converter {

    public static String decimalToAnother(int targetBase, String input) {
        BigInteger target = BigInteger.valueOf(targetBase);
        BigInteger baseNumber = new BigInteger(input);
        StringBuilder result = new StringBuilder();

        while (baseNumber.compareTo(BigInteger.valueOf(targetBase)) > -1) {
            int quotient = baseNumber.remainder(target).intValue();
            baseNumber = baseNumber.divide(target);

            if (quotient < 10) {
                result.append(quotient);
            } else {
                result.append((char) (quotient + 87));
            }
        }
        if (baseNumber.compareTo(BigInteger.TEN) < 0) {
            result.append(baseNumber);
        } else {
            result.append((char) (baseNumber.intValue() + 87));
        }
        return result.reverse().toString();
    }


    public static String anotherToDecimal(int sourceBase, String input) {
        StringBuilder baseNumber = new StringBuilder(input).reverse();
        long result = 0;

        for (int i = 0; i < baseNumber.length(); i++) {
            char literal = baseNumber.charAt(i);
            int literalValue = literal > 58 ? literal - 87 : literal - 48;
            result += literalValue * Math.pow(sourceBase, i);
        }
        return String.valueOf(result);
    }


    public static String anotherFactorialToDecimal(int sourceBase, String baseNumber) {
        double result = 0;

        for (int i = 0; i < baseNumber.length(); i++) {
            char literal = baseNumber.charAt(i);
            int literalValue = literal > 58 ? literal - 87 : literal - 48;
            result += literalValue * Math.pow(sourceBase, -(i + 1));
        }
        return String.format("%.5f", result).replaceFirst("0\\.|0,", "");
    }

    public static String decimalFactorialToAnother(int targetBase, String input) {
        double number = Double.parseDouble("0." + input);
        StringBuilder result = new StringBuilder();

        int infiniteBreaker = 5;
        while (number > 0 && infiniteBreaker > 0) {
            infiniteBreaker--;
            number = number * targetBase;
            int decimalPart = (int) number;

            if (decimalPart < 10) {
                result.append(decimalPart);
            } else {
                result.append((char) (decimalPart + 87));
            }
            number -= decimalPart;
        }
        result.append("00000");
        return new String(result).substring(0, 5);
    }
}

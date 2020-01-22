package com.infinity.converter;

import java.math.BigInteger;

public class NumberConverter {

    public static final String BINARY = "01";
    public static final String TERNARY = "012";
    public static final String OCTAL = "01234567";
    public static final String NONARY = "012345678";
    public static final String DECIMAL = "0123456789";
    public static final String DUODECIMAL = "0123456789AB";
    public static final String HEXADECIMAL = "0123456789ABCDEF";
    public static final String VIGESIMAL = "0123456789ABCDEFGHIJ";

    public static boolean uniqueChar(String str) {
        for (int i = 0; i < str.length(); i++)
            for (int j = i + 1; j < str.length(); j++)
                if (str.charAt(i) == str.charAt(j))
                    return false;
        return true;
    }

    private static boolean charExist(String inputNumber, String digits) {
        for (int i = 0; i < inputNumber.length(); i++)
            if (digits.indexOf(inputNumber.charAt(i)) == -1) return false;
        return true;
    }

    public static BigInteger toDec(String digits, String inputNumber){
        if (!uniqueChar(digits)) return null;
        if (!charExist(inputNumber, digits)) return null;
        int length = inputNumber.length();
        int base = digits.length();
        BigInteger power = BigInteger.ONE;
        BigInteger num = BigInteger.ZERO;
        for (int i = length - 1; i >= 0; i--) {
            BigInteger temp = new BigInteger(Integer.toString(digits.indexOf(inputNumber.charAt(i))));
            num = num.add(temp.multiply(power));
            power = power.multiply(new BigInteger(Integer.toString(base)));
        }
        return num;
    }

    public static BigInteger binToDec(String number) {
        return toDec(BINARY,number);
    }

    public static BigInteger hexToDec(String number) {
        return toDec(HEXADECIMAL,number);
    }

    public static String fromDec(String digits, BigInteger number) {
        if (!uniqueChar(digits)) return null;
        BigInteger base = new BigInteger(Integer.toString(digits.length()));
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(digits.charAt(number.mod(base).intValue()));
            number = number.divide(base);
        }
        while (number.compareTo(BigInteger.ZERO) > 0);
        return sb.reverse().toString();
    }

    public static String anyToAny(String inputDigits, String outputDigits, String inputNumber) {
        if ("".equals(inputNumber)) return "";
        return fromDec(outputDigits, toDec(inputDigits, inputNumber));
    }

    public static boolean isValid(String digits, String number) {
        for (int i = 0; i < number.length(); i++)
            if (!digits.contains(Character.toString(number.charAt(i)))) return false;
        return true;
    }

}

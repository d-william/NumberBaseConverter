package com.infinity.converter;

import java.math.BigInteger;
import java.util.Objects;

public class PositionalNumeralSystem implements NumeralSystem<BigInteger> {

    public static final String BINARY_DIGITS = "01";
    public static final String TERNARY_DIGITS = "012";
    public static final String OCTAL_DIGITS = "01234567";
    public static final String NONARY_DIGITS = "012345678";
    public static final String DECIMAL_DIGITS = "0123456789";
    public static final String DUODECIMAL_DIGITS = "0123456789AB";
    public static final String HEXADECIMAL_DIGITS = "0123456789ABCDEF";
    public static final String VIGESIMAL_DIGITS = "0123456789ABCDEFGHIJ";

    public static final PositionalNumeralSystem BINARY_SYSTEM = new PositionalNumeralSystem(BINARY_DIGITS);
    public static final PositionalNumeralSystem TERNARY_SYSTEM = new PositionalNumeralSystem(TERNARY_DIGITS);
    public static final PositionalNumeralSystem OCTAL_SYSTEM = new PositionalNumeralSystem(OCTAL_DIGITS);
    public static final PositionalNumeralSystem NONARY_SYSTEM = new PositionalNumeralSystem(NONARY_DIGITS);
    public static final PositionalNumeralSystem DECIMAL_SYSTEM = new PositionalNumeralSystem(DECIMAL_DIGITS);
    public static final PositionalNumeralSystem DUODECIMAL_SYSTEM = new PositionalNumeralSystem(DUODECIMAL_DIGITS);
    public static final PositionalNumeralSystem HEXADECIMAL_SYSTEM = new PositionalNumeralSystem(HEXADECIMAL_DIGITS);
    public static final PositionalNumeralSystem VIGESIMAL_SYSTEM = new PositionalNumeralSystem(VIGESIMAL_DIGITS);

    private final String digits;

    /**
     * Constructs a new positional numeral system.
     * 
     * @param digits the digits in order
     */
    public PositionalNumeralSystem(String digits) {
        Objects.requireNonNull(digits, "The digits specified cannot be null");
        if (!uniqueChars(digits)) throw new IllegalArgumentException("The digits specified has duplicate figures");
        this.digits = digits;
    }

    /**
     * Gets the digits of the numeral system in order.
     * 
     * @return a {@code String}
     */
    public String digits() {
        return digits;
    }

    /**
     * Gets the radix of the positional numeral system.
     *
     * @return an {@code int}
     */
    public int radix() {
        return digits.length();
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        if (value.isEmpty()) return false;
        int start = value.charAt(0) == '-' ? 1 : 0;
        if (start == 1 && value.length() == 1) return false;
        for (int i = start; i < value.length(); i++)
            if (digits.indexOf(value.charAt(i)) == -1) return false;
        return true;
    }

    @Override
    public BigInteger parse(String value) {
        Objects.requireNonNull(value, "The value specified cannot be null");
        int length = value.length();
        int base = digits.length();
        BigInteger power = BigInteger.ONE;
        BigInteger num = BigInteger.ZERO;
        int end = value.charAt(0) == '-' ? 1 : 0;
        for (int i = length - 1; i >= end; i--) {
            int index = digits.indexOf(value.charAt(i));
            if (index == -1) throw new NumberFormatException("The value specified is not valid for this base (illegal digit : " + value.charAt(i) + ")");
            BigInteger temp = new BigInteger(Integer.toString(index));
            num = num.add(temp.multiply(power));
            power = power.multiply(new BigInteger(Integer.toString(base)));
        }
        return end == 1 ? num.negate() : num;
    }

    @Override
    public String parse(BigInteger value) {
        Objects.requireNonNull(value, "The value specified cannot be null");
        boolean isNegative = value.signum() == -1;
        value = value.abs();
        BigInteger base = new BigInteger(Integer.toString(digits.length()));
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(digits.charAt(value.mod(base).intValue()));
            value = value.divide(base);
        }
        while (value.compareTo(BigInteger.ZERO) > 0);
        if (isNegative) sb.append('-');
        return sb.reverse().toString();
    }

    private static boolean uniqueChars(String str) {
        for (int i = 0; i < str.length(); i++)
            for (int j = i + 1; j < str.length(); j++)
                if (str.charAt(i) == str.charAt(j))
                    return false;
        return true;
    }

}

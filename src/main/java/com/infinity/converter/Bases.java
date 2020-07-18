package com.infinity.converter;

public abstract class Bases {

    private Bases() {}

    public static String anyToAny(PositionalNumeralSystem from, PositionalNumeralSystem to, String value) {
        return to.parse(from.parse(value));
    }

    public static String anyToAny(String from, String to, String value) {
        return anyToAny(new PositionalNumeralSystem(from), new PositionalNumeralSystem(to), value);
    }

    public static String binToDec(String value) {
        return anyToAny(PositionalNumeralSystem.BINARY_SYSTEM, PositionalNumeralSystem.DECIMAL_SYSTEM, value);
    }

    public static String binToHex(String value) {
        return anyToAny(PositionalNumeralSystem.BINARY_SYSTEM, PositionalNumeralSystem.HEXADECIMAL_SYSTEM, value);
    }

    public static String decToBin(String value) {
        return anyToAny(PositionalNumeralSystem.DECIMAL_SYSTEM, PositionalNumeralSystem.BINARY_SYSTEM, value);
    }

    public static String decToHex(String value) {
        return anyToAny(PositionalNumeralSystem.DECIMAL_SYSTEM, PositionalNumeralSystem.HEXADECIMAL_SYSTEM, value);
    }

    public static String hexToBin(String value) {
        return anyToAny(PositionalNumeralSystem.HEXADECIMAL_SYSTEM, PositionalNumeralSystem.BINARY_SYSTEM, value);
    }

    public static String hexToDec(String value) {
        return anyToAny(PositionalNumeralSystem.HEXADECIMAL_SYSTEM, PositionalNumeralSystem.DECIMAL_SYSTEM, value);
    }

}

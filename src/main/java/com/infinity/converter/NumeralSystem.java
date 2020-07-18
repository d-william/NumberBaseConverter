package com.infinity.converter;

public interface NumeralSystem<T extends Number> {

    /**
     * Checks if the string argument is a valid written form of a number of this numeral system.
     *
     * @param  value a {@code String} to be validated
     * @return true if the string argument is a valid written form of a number of this numeral system;
     *         false otherwise
     */
    boolean isValid(String value);

    /**
     * Parses the written form specified by the string argument as a number.
     * The string argument must be a written form of a number of this numeral system.
     *
     * @param      value a {@code String} containing the number representation to be parsed
     * @return     a value represented by the argument in this numeral system
     * @exception  NumberFormatException if the string is not parsable in this numeral system
     */
    T parse(String value);

    /**
     * Returns a {@code String} object representing the written form of the specified value.
     *
     * @param   value   a number to be converted
     * @return  a string representation of the argument in this numeral system
     */
    String parse(T value);

}

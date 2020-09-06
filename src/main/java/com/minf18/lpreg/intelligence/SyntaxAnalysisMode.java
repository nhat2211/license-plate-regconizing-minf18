

package com.minf18.lpreg.intelligence;

/**
 * Enum to represent the three different syntax analysis modes used when parsing a recognized plate.
 * @see com.minf18.lpreg.intelligence.parser.Parser
 */
public enum SyntaxAnalysisMode {
    DO_NOT_PARSE,
    ONLY_EQUAL_LENGTH,
    EQUAL_OR_SHORTER_LENGTH;

    /**
     * This method is used to convert an integer to an enum constant of this type.
     * 0 maps to {@code DO_NOT_PARSE}, 1 maps to {@code ONLY_EQUAL_LENGTH} and
     * 2 maps to {@code EQUAL_OR_SHORTER_LENGTH}.
     * @param syntaxAnalysisModeInt An integer which should be converted to SyntaxAnalysisMode
     * @return SyntaxAnalysisMode A constant of this enum type which represents syntaxAnalysisModeInt
     * @throws IllegalArgumentException If given an integer which isn't 0, 1 or 2
     */
    public static SyntaxAnalysisMode getSyntaxAnalysisModeFromInt(int syntaxAnalysisModeInt) {
        switch (syntaxAnalysisModeInt) {
            case 0:
                return SyntaxAnalysisMode.DO_NOT_PARSE;
            case 1:
                return SyntaxAnalysisMode.ONLY_EQUAL_LENGTH;
            case 2:
                return SyntaxAnalysisMode.EQUAL_OR_SHORTER_LENGTH;
            default:
                throw new IllegalArgumentException("Expected: 0, 1, or 2. Got: " + syntaxAnalysisModeInt);
        }
    }
}

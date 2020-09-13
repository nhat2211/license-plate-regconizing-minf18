

package com.minf18.lpreg.intelligence;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SyntaxAnalysisModeTest {

    @Test
    public void testGetSyntaxAnalysisModeFromIntReturnsDoNotParseWhenIntIsZero() {
        assertEquals(SyntaxAnalysisMode.getSyntaxAnalysisModeFromInt(0), SyntaxAnalysisMode.DO_NOT_PARSE);
    }

    @Test
    public void testGetSyntaxAnalysisModeFromIntReturnsOnlyEqualLengthWhenIntIsOne() {
        assertEquals(SyntaxAnalysisMode.getSyntaxAnalysisModeFromInt(1), SyntaxAnalysisMode.ONLY_EQUAL_LENGTH);
    }

    @Test
    public void testGetSyntaxAnalysisModeFromIntReturnsEqualOrShorterLengthWhenIntIsTwo() {
        assertEquals(SyntaxAnalysisMode.getSyntaxAnalysisModeFromInt(2), SyntaxAnalysisMode.EQUAL_OR_SHORTER_LENGTH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSyntaxAnalysisModeThrowsIllegalArgumentExceptionWhenGivenIntNotEqualToOneTwoOrThree() {
        SyntaxAnalysisMode.getSyntaxAnalysisModeFromInt(3);
    }
}

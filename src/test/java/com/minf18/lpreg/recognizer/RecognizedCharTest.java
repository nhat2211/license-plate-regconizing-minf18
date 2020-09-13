

package com.minf18.lpreg.recognizer;

import com.minf18.lpreg.test.util.TestUtility;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RecognizedCharTest {
    private RecognizedChar recognizedChar;

    @Before
    public void setup() {
        recognizedChar = new RecognizedChar();
        recognizedChar.addPattern(new RecognizedPattern('A', 3.0f));
        recognizedChar.addPattern(new RecognizedPattern('B', 1.0f));
        recognizedChar.addPattern(new RecognizedPattern('C', 4.0f));
    }

    @Test
    public void testPatternsCorrectlySortedAscending() {
        assertFalse(recognizedChar.isSorted());
        recognizedChar.sort(false);
        assertTrue(recognizedChar.isSorted());
        List<RecognizedPattern> patterns = recognizedChar.getPatterns();
        assertEquals(patterns.get(0).getCost(), 1.0f, TestUtility.epsilon);
        assertEquals(patterns.get(1).getCost(), 3.0f, TestUtility.epsilon);
        assertEquals(patterns.get(2).getCost(), 4.0f, TestUtility.epsilon);
    }

    @Test
    public void testPatternsCorrectlySortedDescending() {
        assertFalse(recognizedChar.isSorted());
        recognizedChar.sort(true);
        assertTrue(recognizedChar.isSorted());
        List<RecognizedPattern> patterns = recognizedChar.getPatterns();
        assertEquals(patterns.get(0).getCost(), 4.0f, TestUtility.epsilon);
        assertEquals(patterns.get(1).getCost(), 3.0f, TestUtility.epsilon);
        assertEquals(patterns.get(2).getCost(), 1.0f, TestUtility.epsilon);
    }

    @Test
    public void testGetPatternReturnsCorrectPatternWhenPatternsSorted() {
        recognizedChar.sort(false);
        assertEquals(recognizedChar.getPattern(2).getCost(), 4.0f, TestUtility.epsilon);
    }

    @Test
    public void testGetPatternReturnsNullWhenPatternsNotSorted() {
        assertNull(recognizedChar.getPattern(2));
    }
}

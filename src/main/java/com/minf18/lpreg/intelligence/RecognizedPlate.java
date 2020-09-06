
package com.minf18.lpreg.intelligence;

import com.minf18.lpreg.recognizer.RecognizedChar;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a number plate being recognized. As each character (represented by {@link RecognizedChar})
 * is recognized it gets added to the {@code RecognizedPlate}.
 */
public class RecognizedPlate {

    private final List<RecognizedChar> chars;

    /**
     * Constructs a new {@code RecognizedPlate} with no {@code RecognizedChar}s.
     */
    public RecognizedPlate() {
        chars = new ArrayList<>();
    }

    /**
     * Adds one instance of {@link RecognizedChar} to the list of {@code RecognizedChar}s which have been
     * recognized for this plate.
     * @param chr The new {@link RecognizedChar} to be added
     */
    public void addChar(RecognizedChar chr) {
        chars.add(chr);
    }

    /**
     * This method is for getting a specific {@link RecognizedChar} which has been added to this plate.
     * @param i The index of the {@link RecognizedChar} to be returned
     * @return The {@link RecognizedChar} which was the {@code i}th one to be added to this plate (starting from 0)
     * @throws ArrayIndexOutOfBoundsException if {@code i} is larger than the number of {@code RecognizedChar}s added
     * - 1 or {@code i} is less than 0.
     */
    public RecognizedChar getChar(int i) {
        return chars.get(i);
    }

    /**
     * This method is for getting a string representation of all the {@code RecognizedChar}s added to this plate.
     * @return A string is made up of the character stored in the first pattern of each {@link RecognizedChar} with
     * a space in between each one.
     */
    public String getString() {
        StringBuilder sb = new StringBuilder();
        for (RecognizedChar aChar : chars) {
            sb.append(aChar.getPattern(0).getChar());
        }
        return sb.toString();
    }

    /**
     * @return All the {@code RecognizedChar}s added to this plate
     */
    public List<RecognizedChar> getChars() {
        return chars;
    }
}

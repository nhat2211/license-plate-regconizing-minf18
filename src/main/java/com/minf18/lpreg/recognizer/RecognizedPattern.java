

package com.minf18.lpreg.recognizer;

public final class RecognizedPattern {
    private final char chr;
    private final float cost;

    public RecognizedPattern(char chr, float value) {
        this.chr = chr;
        cost = value;
    }

    public char getChar() {
        return chr;
    }

    public float getCost() {
        return cost;
    }
}

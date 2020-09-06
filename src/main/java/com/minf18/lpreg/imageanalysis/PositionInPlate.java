

package com.minf18.lpreg.imageanalysis;

/**
 * The coordinates of a character in the plate.
 */
public class PositionInPlate { // TODO refactor

    /**
     * Left X coordinate.
     */
    public final int x1;

    /**
     * Right X coordinate.
     */
    public final int x2;

    public PositionInPlate(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }
}

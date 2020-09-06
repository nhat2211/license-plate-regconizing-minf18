

package com.minf18.lpreg.test.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Utility class which helps in having methods for Testing.
 */
public final class TestUtility {
    public static final double epsilon = 5.96e-08;

    private TestUtility() {
        // intentionally empty
    }

    public static StringBuilder readFile(final String filename) throws IOException {
        final BufferedReader br = new BufferedReader(new FileReader(filename));
        final StringBuilder sb = new StringBuilder();
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            sb.append(currentLine);
        }
        return sb;
    }

    public static double average(List<? extends Number> list) {
        if (list == null || list.size() == 0) {
            throw new IllegalStateException("Cannot average null or empty list.");
        }
        return list.stream().mapToDouble(Number::doubleValue).sum() / (double) list.size();
    }

}

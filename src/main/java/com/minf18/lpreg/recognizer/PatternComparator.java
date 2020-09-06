

package com.minf18.lpreg.recognizer;

import java.util.Comparator;

/**
 * Comparator for comparing {@link RecognizedPattern}.
 */
public class PatternComparator implements Comparator<RecognizedPattern> {
    private final boolean shouldSortDescending;

    /**
     * Constructs a new PatternComparator which will compare instances of {@link RecognizedPattern}.
     * @param shouldSortDescending This is used to decide how to compare two instances of {@link RecognizedPattern}
     *                             in {@link PatternComparator#compare(RecognizedPattern, RecognizedPattern)}
     */
    public PatternComparator(boolean shouldSortDescending) {
        this.shouldSortDescending = shouldSortDescending;
    }

    /**
     *
     * @param recognizedPattern1 The instance of {@link RecognizedPattern} to compare
     * @param recognizedPattern2 The instance of {@link RecognizedPattern} to compare
     * @return If this instance of {@link PatternComparator}
     * has been constructed with {@code shouldSortDescending} being true, then this will return 1 if
     * recognizedPattern1 is smaller and -1 if recognizedPattern2 is smaller. Otherwise it will return -1
     * if recognizedPattern1 is smaller and 1 if recognizedPattern2 is smaller. If the two objects are equal,
     * it will return 0. It uses the cost of the patterns to compare them.
     */
    @Override
    public int compare(RecognizedPattern recognizedPattern1, RecognizedPattern recognizedPattern2) {
        Float cost1 = recognizedPattern1.getCost();
        Float cost2 = recognizedPattern2.getCost();
        return shouldSortDescending ? -1 * cost1.compareTo(cost2) : cost1.compareTo(cost2);
    }
}

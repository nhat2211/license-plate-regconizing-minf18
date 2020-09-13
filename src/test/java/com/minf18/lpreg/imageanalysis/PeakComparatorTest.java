
package com.minf18.lpreg.imageanalysis;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PeakComparatorTest {
    PeakComparator peakComparator;
    Peak peak1;
    Peak peak2;

    @Before
    public void setup() {
        List<Float> yValues = Arrays.asList(1.0f, 2.0f);
        peakComparator = new PeakComparator(yValues);
        peak1 = mock(Peak.class);
        peak2 = mock(Peak.class);
    }

    @Test
    public void testCompareEqualPeaksReturnsZero() {
        when(peak1.getCenter()).thenReturn(0);
        when(peak2.getCenter()).thenReturn(0);
        assertEquals(peakComparator.compare(peak1, peak2), 0);
    }

    @Test
    public void testCompareFirstPeakSmallerReturnsResultGreaterThanZero() {
        when(peak1.getCenter()).thenReturn(0);
        when(peak2.getCenter()).thenReturn(1);
        int comparisonResult = peakComparator.compare(peak1, peak2);
        assertTrue("Expecting a result which is greater than 0 when comparing peak with centre " + peak1.getCenter()
                + " to peak with centre " + peak2.getCenter() + ". Got result " + comparisonResult,
                comparisonResult > 0);
    }

    @Test
    public void testCompareFirstPeakLargerReturnsResultLessThanZero() {
        when(peak1.getCenter()).thenReturn(1);
        when(peak2.getCenter()).thenReturn(0);
        int comparisonResult = peakComparator.compare(peak1, peak2);
        assertTrue("Expecting a result which is less than 0 when comparing peak with centre " + peak1.getCenter()
                + " to peak with centre " + peak2.getCenter() + ". Got result " + comparisonResult,
                comparisonResult < 0);
    }
}

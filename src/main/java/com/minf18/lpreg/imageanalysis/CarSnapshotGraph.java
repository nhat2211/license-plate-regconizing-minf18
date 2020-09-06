
package com.minf18.lpreg.imageanalysis;

import com.minf18.lpreg.configurator.Configurator;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for searching bands in an image.
 */
public class CarSnapshotGraph extends Graph {

    private static final double peakFootConstant =
            Configurator.getConfigurator().getDoubleProperty("carsnapshotgraph_peakfootconstant"); // 0.55
    private static final double peakDiffMultiplicationConstant =
            Configurator.getConfigurator().getDoubleProperty("carsnapshotgraph_peakDiffMultiplicationConstant"); // 0.1

    public List<Peak> findPeaks(int count) {
        List<Peak> outPeaks = new ArrayList<>();
        for (int c = 0; c < count; c++) {
            float maxValue = 0.0f;
            int maxIndex = 0;
            for (int i = 0; i < yValues.size(); i++) { // left to right
                if (allowedInterval(outPeaks, i)) {
                    if (yValues.get(i) >= maxValue) {
                        maxValue = yValues.get(i);
                        maxIndex = i;
                    }
                }
            }
            // we found the biggest peak
            int leftIndex = indexOfLeftPeakRel(maxIndex, CarSnapshotGraph.peakFootConstant);
            int rightIndex = indexOfRightPeakRel(maxIndex, CarSnapshotGraph.peakFootConstant);
            int diff = rightIndex - leftIndex;
            leftIndex -= CarSnapshotGraph.peakDiffMultiplicationConstant * diff;
            rightIndex += CarSnapshotGraph.peakDiffMultiplicationConstant * diff;
            outPeaks.add(new Peak(Math.max(0, leftIndex), maxIndex, Math.min(yValues.size() - 1, rightIndex)));
        }
        outPeaks.sort(new PeakComparator(yValues));
        super.peaks = outPeaks;
        return outPeaks;
    }
}



package com.minf18.lpreg.recognizer;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class RecognizedChar {
    private final List<RecognizedPattern> patterns;
    private boolean isSorted;

    public RecognizedChar() {
        patterns = new ArrayList<>();
        isSorted = false;
    }

    public void addPattern(RecognizedPattern pattern) {
        patterns.add(pattern);
    }

    public boolean isSorted() {
        return isSorted;
    }

    public void sort(boolean shouldSortDescending) {
        if (isSorted) {
            return;
        }
        isSorted = true;
        patterns.sort(new PatternComparator(shouldSortDescending));
    }

    /**
     * @return null if not sorted
     */
    public List<RecognizedPattern> getPatterns() {
        if (isSorted) {
            return patterns;
        }
        return null;
    }

    public RecognizedPattern getPattern(int i) {
        if (isSorted) {
            return patterns.get(i);
        }
        return null;
    }

    public BufferedImage render() {
        int width = 500;
        int height = 200;
        BufferedImage histogram = new BufferedImage(width + 20, height + 20, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = histogram.createGraphics();
        graphic.setColor(Color.LIGHT_GRAY);
        Rectangle backRect = new Rectangle(0, 0, width + 20, height + 20);
        graphic.fill(backRect);
        graphic.draw(backRect);
        graphic.setColor(Color.BLACK);

        int colWidth = width / patterns.size();
        int left, top;
        for (int ay = 0; ay <= 100; ay += 10) {
            int y = 15 + (int) (((100 - ay) / 100.0f) * (height - 20));
            graphic.drawString(Integer.toString(ay), 3, y + 11);
            graphic.drawLine(25, y + 5, 35, y + 5);
        }
        graphic.drawLine(35, 19, 35, height);
        graphic.setColor(Color.BLUE);
        for (int i = 0; i < patterns.size(); i++) {
            left = (i * colWidth) + 42;
            top = height - (int) (patterns.get(i).getCost() * (height - 20));
            graphic.drawRect(left, top, colWidth - 2, height - top);
            graphic.drawString(patterns.get(i).getChar() + " ", left + 2, top - 8);
        }
        return histogram;
    }
}

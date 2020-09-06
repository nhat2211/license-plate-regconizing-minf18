

package com.minf18.lpreg.test.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * A simple utility class to view an image - opens a GUI window (JFrame).
 */
class SimpleImageViewer {

    private final JFrame frame;
    private final BufferedImage img;

    public SimpleImageViewer(BufferedImage img) {
        this.img = img;
        frame = new JFrame("WINDOW");
        frame.setVisible(true);
        start();
        frame.add(new JLabel(new ImageIcon(getImage())));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private Image getImage() {
        return img;
    }

    private void start() {
        while (true) {
            BufferStrategy bs = frame.getBufferStrategy();
            if (bs == null) {
                frame.createBufferStrategy(4);
                return;
            }
            Graphics g = bs.getDrawGraphics();
            g.drawImage(img, 0, 0, 800, 600, null);
            g.dispose();
            bs.show();
        }
    }
}

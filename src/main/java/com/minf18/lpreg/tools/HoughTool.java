

package com.minf18.lpreg.tools;

import com.minf18.lpreg.imageanalysis.HoughTransformation;
import com.minf18.lpreg.imageanalysis.Photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class HoughTool {

    private HoughTool() {
        // intentionally empty
    }

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        FileInputStream fis = new FileInputStream(file);
        Photo p = new Photo(fis);
        HoughTransformation hough = p.getHoughTransformation();
        Photo transformed =
                new Photo(hough.render(HoughTransformation.RENDER_TRANSFORMONLY, HoughTransformation.COLOR_HUE));
        transformed.saveImage(args[1]);
        p.close();
        transformed.close();
    }
}

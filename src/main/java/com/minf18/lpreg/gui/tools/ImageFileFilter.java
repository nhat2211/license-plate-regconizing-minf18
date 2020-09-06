

package com.minf18.lpreg.gui.tools;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * This class filters filenames using the filename extension.
 */
public class ImageFileFilter extends FileFilter {

    /**
     * This method will accept the given filename if its extension is .bmp, .jpg, .jpeg, .png or .gif.
     * @param name The filename to check
     * @return true if the filename has one of the accepted extensions
     */
    public static boolean accept(String name) {
        int lastIndex = name.lastIndexOf('.');
        if (lastIndex < 0) {
            return false;
        }
        String type = name.substring(lastIndex + 1, name.length()).toLowerCase();
        return type.equals("bmp") || type.equals("jpg") || type.equals("jpeg") || type.equals("png") || type
                .equals("gif");
    }

    /**
     * This method will accept the given file if it is a directory or if it has a filename which is accepted
     * by {@link ImageFileFilter#accept(String)}.
     * @param file The file to check
     * @return true if the file is a directory or its filename is accepted by {@link ImageFileFilter#accept(String)}
     */
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String name = file.getName();
        return accept(name);
    }

    /**
     * This method returns a description of the file extensions which are currently accepted.
     * @return "images (*.jpg, *.bmp, *.gif, *.png)"
     */
    @Override
    public String getDescription() {
        return "images (*.jpg, *.bmp, *.gif, *.png)";
    }
}

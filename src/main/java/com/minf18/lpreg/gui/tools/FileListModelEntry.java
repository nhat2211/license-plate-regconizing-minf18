

package com.minf18.lpreg.gui.tools;

public class FileListModelEntry {

    private final String fileName;
    private final String fullPath;
    private String recognizedPlate = "?";

    public FileListModelEntry(String fileName, String fullPath) {
        this.fileName = fileName;
        this.fullPath = fullPath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getRecognizedPlate() {
        return recognizedPlate;
    }

    public void setRecognizedPlate(String recognizedPlate) {
        this.recognizedPlate = recognizedPlate;
    }

    @Override
    public String toString() {
        return fileName;
    }
}

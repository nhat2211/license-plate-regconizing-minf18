
package com.minf18.lpreg.jar;

import com.minf18.lpreg.configurator.Configurator;
import com.minf18.lpreg.gui.ReportGenerator;
import com.minf18.lpreg.gui.windows.FrameComponentInit;
import com.minf18.lpreg.gui.windows.FrameMain;
import com.minf18.lpreg.imageanalysis.CarSnapshot;
import com.minf18.lpreg.imageanalysis.Char;
import com.minf18.lpreg.intelligence.Intelligence;
import com.minf18.lpreg.recognizer.NeuralPatternClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;


public final class Main {

    private static final transient Logger logger = LoggerFactory.getLogger(Main.class);
    /**
     * The report generator.
     */
    public static ReportGenerator rg;
    /**
     * The intelligence.
     */
    public static Intelligence systemLogic;
    /**
     * The help message.
     */
    public static final String helpText = "MINF18";
    static {
        try {
            rg = new ReportGenerator("report");
        } catch (IOException e) {
            throw new IllegalStateException("Error during report generator initialization.", e);
        }
    }

    private Main() {
        // intentionally empty
    }

    /**
     * Normalizes the alphabet in the source directory and writes the result to the target directory.
     *
     * @param srcdir the source directory
     * @param dstdir the destination directory
     * @throws IOException an IOException
     * @deprecated not used
     */
    public static void newAlphabet(String srcdir, String dstdir) throws IOException {
        int x = Configurator.getConfigurator().getIntProperty("char_normalizeddimensions_x");
        int y = Configurator.getConfigurator().getIntProperty("char_normalizeddimensions_y");
        logger.info("\nCreating new alphabet (" + x + " x " + y + " px)... \n");
        for (String fileName : Char.getAlphabetList(srcdir)) {
            Char c = new Char(fileName);
            c.normalize();
            c.saveImage(dstdir + File.separator + fileName);
            logger.info(fileName + " done");
            c.close();
        }
    }

    /**
     * Train neural network according to specified feature extraction method and learning parameters (in config file)
     * and saves it into output file.
     *
     * @param destinationFile the destination file
     * @throws Exception an Exception
     */
    public static void learnAlphabet(String destinationFile) throws Exception {
        File f = new File(destinationFile);
        try {
            if (!f.createNewFile()) {
                throw new IOException("File already exists.");
            }
        } catch (Exception e) {
            throw new IOException("Can't find the path specified.", e);
        }
        NeuralPatternClassifier npc = new NeuralPatternClassifier(true);
        npc.getNetwork().saveToXml(destinationFile);
    }

    /**
     * Main method which parses the input parameters and then runs the project accordingly.
     *
     * @param args the input parameters
     * @throws Exception an Exception
     */
    public static void main(String[] args) throws Exception { // TODO refactor
        if ((args.length == 0) || ((args.length == 1) && args[0].equals("-gui"))) { // gui
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            FrameComponentInit frameComponentInit = new FrameComponentInit(); // show wait
            Main.systemLogic = new Intelligence();
            frameComponentInit.dispose(); // hide wait
            new FrameMain();
        } else if ((args.length == 3) && args[0].equals("-recognize") && args[1].equals("-i")) {
            // load snapshot args[2] and recognize it
            Main.systemLogic = new Intelligence();
            System.out.println(Main.systemLogic.recognize(new CarSnapshot(args[2]), false));
        } else if ((args.length == 5) && args[0].equals("-recognize") && args[1].equals("-i") && args[3].equals("-o")) {
            // load snapshot arg[2] and generate report into arg[4]
            Main.rg = new ReportGenerator(args[4]);
            Main.systemLogic = new Intelligence();
            Main.systemLogic.recognize(new CarSnapshot(args[2]), true);
        } else if ((args.length == 3) && args[0].equals("-newconfig") && args[1].equals("-o")) {
            // save default config into args[2]
            Configurator.getConfigurator().saveConfiguration(args[2]);
        } else if ((args.length == 3) && args[0].equals("-newnetwork") && args[1].equals("-o")) {
            // learn new neural network and save it into into args[2]
            Main.learnAlphabet(args[2]);
        } else if ((args.length == 5) && args[0].equals("-newalphabet") && args[1].equals("-i") && args[3]
                .equals("-o")) { // transform alphabets from args[2] -> args[4]
            Main.newAlphabet(args[2], args[4]);
        } else { // display help
            System.out.println(Main.helpText);
        }
    }
}

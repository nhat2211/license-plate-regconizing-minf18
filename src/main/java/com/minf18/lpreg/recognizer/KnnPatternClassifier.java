

package com.minf18.lpreg.recognizer;

import com.minf18.lpreg.configurator.Configurator;
import com.minf18.lpreg.imageanalysis.Char;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class KnnPatternClassifier extends CharacterRecognizer {

    private static final transient Logger logger = LoggerFactory.getLogger(KnnPatternClassifier.class);
    private final List<List<Double>> learnLists;

    public KnnPatternClassifier() {
        String path = Configurator.getConfigurator().getPathProperty("char_learnAlphabetPath");
        learnLists = new ArrayList<>(36);
        List<String> filenames = Char.getAlphabetList(path);
        for (String fileName : filenames) {
            Char imgChar;
            try (InputStream is = Configurator.getConfigurator().getResourceAsStream(fileName)) {
                imgChar = new Char(is);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load Char: " + fileName, e);
            }
            imgChar.normalize();
            learnLists.add(imgChar.extractFeatures());
        }
        // check vector elements
        for (List<Double> learnList : learnLists) {
            if (learnList == null) {
                logger.warn("Alphabet in {} is not complete", path);
            }
        }
    }

    @Override
    public RecognizedChar recognize(Char chr) {
        List<Double> tested = chr.extractFeatures();
        RecognizedChar recognized = new RecognizedChar();
        for (int x = 0; x < learnLists.size(); x++) {
            float fx = simplifiedEuclideanDistance(tested, learnLists.get(x));
            recognized.addPattern(new RecognizedPattern(ALPHABET[x], fx));
        }
        recognized.sort(false);
        return recognized;
    }

    /**
     * Simple vector distance.
     *
     * @param vectorA vector A
     * @param vectorB vector B
     * @return their simple distance
     * @deprecated Use {@link #simplifiedEuclideanDistance(List, List)}, works better.
     */
    private float difference(List<Double> vectorA, List<Double> vectorB) {
        float diff = 0;
        for (int x = 0; x < vectorA.size(); x++) {
            diff += Math.abs(vectorA.get(x) - vectorB.get(x));
        }
        return diff;
    }

    /**
     * Worked better than simple vector distance.
     *
     * @param vectorA vector A
     * @param vectorB vector B
     * @return the euclidean distance of A and B
     */
    private float simplifiedEuclideanDistance(List<Double> vectorA, List<Double> vectorB) {
        float diff = 0;
        float partialDiff;
        for (int x = 0; x < vectorA.size(); x++) {
            partialDiff = (float) Math.abs(vectorA.get(x) - vectorB.get(x));
            diff += partialDiff * partialDiff;
        }
        return diff;
    }
}

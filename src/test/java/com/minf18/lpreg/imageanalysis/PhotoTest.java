
package com.minf18.lpreg.imageanalysis;

import com.minf18.lpreg.configurator.Configurator;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class PhotoTest {

    @Test
    public void cloneTest() throws Exception {
        InputStream fstream = Configurator.getConfigurator().getResourceAsStream("snapshots/test_041.jpg");
        assertNotNull(fstream);
        Photo photo = new Photo(fstream);
        fstream.close();

        assertNotNull(photo);
        assertNotNull(photo.getImage());

        Photo clone = photo.clone();
        assertEquals(photo, clone);
        assertEquals(photo.hashCode(), clone.hashCode());
        clone.close();
        photo.close();
    }
}

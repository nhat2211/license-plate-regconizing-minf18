
package com.minf18.lpreg.configurator;

import com.minf18.lpreg.test.util.TestUtility;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;

import static org.junit.Assert.assertEquals;

public class ConfiguratorTest {
    private Configurator configurator;
    private final String propertyName = "new property";

    @Before
    public void setup() {
        configurator = Configurator.getConfigurator();
    }

    @Test
    public void testSetAndGetStringProperty() {
        String expectedValue = "new value";
        configurator.setStrProperty(propertyName, expectedValue);
        assertEquals(configurator.getStrProperty(propertyName), expectedValue);
    }

    @Test
    public void testGetPathProperty() {
        String pathAsString = "dir/file";
        String expectedPath = "dir/file".replace('/', File.separatorChar);
        configurator.setStrProperty(propertyName, pathAsString);
        assertEquals(configurator.getPathProperty(propertyName), expectedPath);
    }

    @Test
    public void testSetAndGetIntPropertyWithValidInt() {
        int expectedValue = 5;
        configurator.setIntProperty(propertyName, expectedValue);
        assertEquals(configurator.getIntProperty(propertyName), expectedValue);
    }

    @Test(expected = NumberFormatException.class)
    public void testSetAndGetIntPropertyWithInvalidInt() {
        configurator.setStrProperty(propertyName, "not an int");
        configurator.getIntProperty(propertyName);
    }

    @Test
    public void testSetAndGetDoublePropertyWithValidDouble() {
        double expectedValue = 5.0;
        configurator.setDoubleProperty(propertyName, expectedValue);
        assertEquals(configurator.getDoubleProperty(propertyName), expectedValue, TestUtility.epsilon);
    }

    @Test(expected = NumberFormatException.class)
    public void testSetAndGetDoublePropertyWithInvalidDouble() {
        configurator.setStrProperty(propertyName, "not a double");
        configurator.getDoubleProperty(propertyName);
    }

    @Test
    public void testSetAndGetColourPropertyWithValidColour() {
        configurator.setColorProperty(propertyName, Color.red);
        assertEquals(configurator.getColorProperty(propertyName), Color.red);
    }
}

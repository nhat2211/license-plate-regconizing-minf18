

package com.minf18.lpreg.gui.tools;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ImageFileFilterTest {
    @Test
    public void testAcceptsJpgFileName() {
        assertTrue(ImageFileFilter.accept("some name.jpg"));
    }

    @Test
    public void testAcceptsBmpFileName() {
        assertTrue(ImageFileFilter.accept("some name.bmp"));
    }

    @Test
    public void testAcceptsGifFileName() {
        assertTrue(ImageFileFilter.accept("some name.gif"));
    }

    @Test
    public void testAcceptsPngFileName() {
        assertTrue(ImageFileFilter.accept("some name.png"));
    }

    @Test
    public void testDoesNotAcceptFileNameWithoutExtension() {
        assertFalse(ImageFileFilter.accept("some name"));
    }

    @Test
    public void testDoesNotAcceptFileNameWithIncorrectExtension() {
        assertFalse(ImageFileFilter.accept("some name.txt"));
    }

    @Test
    public void testAcceptsDirectory() {
        File mockedFile = mock(File.class);
        when(mockedFile.isDirectory()).thenReturn(true);
        assertTrue(new ImageFileFilter().accept(mockedFile));
    }
}

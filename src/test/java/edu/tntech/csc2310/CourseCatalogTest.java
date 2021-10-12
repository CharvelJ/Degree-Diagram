package edu.tntech.csc2310;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CourseCatalogTest {

    private CourseCatalog catalog;

    @Before
    public void setUp() throws Exception {
        catalog = new CourseCatalog("CSC", "202180");
    }

    @Test
    public void getCourse() {

        Course c = catalog.getCourse("2770");
        assertEquals("Course test", "Intro to Systems & Networking", c.getTitle());
        Course c1 = catalog.getCourse("2001");
        assertNull(c1);

    }


    @Test
    public void getCourseNumbers() {
        ArrayList list = CourseCatalog.getCourseNumbers("CSC", "202180");
        assertEquals("CourseNumber test", 124, list.size());
        list = CourseCatalog.getCourseNumbers("COMM", "202180");
        assertEquals("CourseNumber test", 47, list.size());
    }
}
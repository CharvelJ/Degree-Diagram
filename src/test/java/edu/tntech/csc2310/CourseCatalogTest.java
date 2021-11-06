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
        assertEquals("Course test credits", 3, c.getCredits());
        Course c1 = catalog.getCourse("2001");
        assertNull(c1);
    }

    @Test
    public void getCourseNumbers() {
        ArrayList list = catalog.getCourses();
        assertEquals("CourseNumber test", 62, list.size());
        CourseCatalog c2 = new CourseCatalog("COMM", "202180");
        list = c2.getCourses();
        assertEquals("CourseNumber test", 37, list.size());
    }

    @Test
    public void getCatalogYear() {
        assertEquals("Catalog Year", "202180", catalog.getCatalogYear());
    }

    @Test
    public void getSubject() {
        assertEquals("Subject", "CSC", catalog.getSubject());
    }
}
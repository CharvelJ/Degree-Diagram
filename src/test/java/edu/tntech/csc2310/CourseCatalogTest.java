package edu.tntech.csc2310;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CourseCatalogTest {

    public static CourseCatalog catalog;

    @BeforeClass
    public static void setUp() throws Exception {
        CourseCatalogTest.catalog = new CourseCatalog("CSC", "202180");
    }

    @Test
    public void getCourse() {
        Course c = CourseCatalogTest.catalog.getCourse("2770");
        assertEquals("Course test", "Intro to Systems & Networking", c.getTitle());
        assertEquals("Course test credits", 3, c.getCredits());
    }

    @Test
    public void getCourseNonExistent() {
        Course c1 = CourseCatalogTest.catalog.getCourse("2001");
        assertNull(c1);
    }

    @Test
    public void badSubject(){
        CourseCatalog c = new CourseCatalog("Missing", "202180");
        assertNull(c.getSubject());
        assertNull(c.getCatalogYear());
    }

    @Test
    public void trimSubject(){
        CourseCatalog c = new CourseCatalog(" CSC ", "202180");
        assertEquals("subject trim", "CSC", c.getSubject());
    }

    @Test
    public void toUpperSubject(){
        CourseCatalog c = new CourseCatalog("math", "202180");
        assertEquals("subject lowercase", "MATH", c.getSubject());
    }


    @Test
    public void getCourseNumbers() {
        ArrayList list = CourseCatalog.getCourseNumbers("CSC", "202180");
        assertEquals("CourseNumber test", 62, list.size());
        list = CourseCatalog.getCourseNumbers("COMM", "202180");
        assertEquals("CourseNumber test", 37, list.size());
    }

    @Test
    public void getCatalogYear() {
        assertEquals("Catalog Year", "202180", CourseCatalogTest.catalog.getCatalogYear());
    }

    @Test
    public void getSubject() {
        assertEquals("Subject", "CSC", CourseCatalogTest.catalog.getSubject());
    }
}
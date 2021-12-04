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

            ///Wrong number.
            Course wrong = catalog.getCourse("3333");
            assertNull(wrong);
    }

    @Test
    public void getCourseNonExistent() {
        Course c1 = CourseCatalogTest.catalog.getCourse("2001");
        assertNull(c1);

        //Wrong catalog year.
        assertNotEquals("Catalog Year", "202280", catalog.getCatalogYear());
    }

    @Test
    public void badSubject() throws CatalogNotFoundException{
        try {
            CourseCatalog c = new CourseCatalog("Missing", "202180");
            assertNull(c.getSubject());
            assertNull(c.getCatalogYear());
        }catch(CatalogNotFoundException e) {
            System.out.println(e);
        }

    }

    @Test
    public void trimSubject() throws CatalogNotFoundException{
        try {
            CourseCatalog c = new CourseCatalog(" CSC ", "202180");
            assertEquals("subject trim", "CSC", c.getSubject());
        }catch(CatalogNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void toUpperSubject() throws CatalogNotFoundException{
        try {
            CourseCatalog c = new CourseCatalog("math", "202180");
            assertEquals("subject lowercase", "MATH", c.getSubject());
        }catch (CatalogNotFoundException e) {
            System.out.println(e);
        }

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

        //Wrong catalog year.
        assertNotEquals("Catalog Year", "202280", catalog.getCatalogYear());
    }

    @Test
    public void getSubject() {
        assertEquals("Subject", "CSC", CourseCatalogTest.catalog.getSubject());

        //Check for lowercase letters
        assertNotEquals("Subject", "csc", catalog.getSubject());
    }
}
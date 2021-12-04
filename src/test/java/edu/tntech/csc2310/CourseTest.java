package edu.tntech.csc2310;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CourseTest {

    Course data;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getSubject() throws CourseNotFoundException {
        try {
            data = new Course("CSC", "1300", "202180");
            assertEquals("Subject", "CSC", data.getSubject());
            data = new Course("COMM", "1020", "202180");
            assertEquals("Subject", "COMM", data.getSubject());

            //Test for extra spaces.
            data = new Course(" C S C", "1300", "202180");
            assertNull(data.getSubject());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void courseNotExists() throws CourseNotFoundException {
        try {
            data = new Course("NURS", "4575", "202180");
            assertNull("Non existent", data.getSubject());
            assertNull("Non existent", data.getNumber());
            assertEquals("Non existent", -1, data.getCredits());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getSubjectNumberTrim() throws CourseNotFoundException {
        try {
            data = new Course("CSC ", "1300", "202180");
            assertEquals("Subject", "CSC", data.getSubject());
            data = new Course(" COMM ", " 1020", " 202180");
            assertEquals("Subject", "COMM", data.getSubject());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getCatalogisNum() throws CourseNotFoundException {
        try {
            data = new Course("COMM ", "1020", "a202180");
        } catch (NumberFormatException ex){
            assertNull(data);
        }
    }

    @Test
    public void getNumber() throws CourseNotFoundException {
        try {
            data = new Course("CSC", "1300", "202180");
            assertEquals("Subject", "1300", data.getNumber());

            //Test for incorrect number
            data = new Course("CSC", "1302", "202180");
            assertNull(data.getNumber());

            //Test for extra spaces.
            data = new Course("CSC ", "1300", "202180");
            assertNull(data.getNumber());

            //Test if subject code is lowercase.
            data = new Course("csc", "1300", "202180");
            assertEquals("Subject", "1300", data.getNumber());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getNumberTrim() throws CourseNotFoundException {
        try {
            data = new Course("COMM ", " 1020 ", "202180");
            assertEquals("Number", "1020", data.getNumber());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getTitle() throws CourseNotFoundException {
        try {
            data = new Course("CSC", "1300", "202180");
            assertEquals("Title", "Intro/Prob Solving-Comp Prog", data.getTitle());

            //Wrong title
            data = new Course("CSC", "1200", "202180");
            assertNotEquals("Title", "Hey now, you're a rockstar.", data.getTitle());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getNullTitle() throws CourseNotFoundException {
        try {
            data = new Course("CSC", "1000", "202180");
            assertNull("No course, Title Null", data.getTitle());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getPrerequisites() throws CourseNotFoundException {
        try {
            // CSC 1300 has three course-based pre-requisites
            data = new Course("CSC", "1300", "202180");
            String[] eresults = {"CSC 1200", "MATH 1845", "MATH 1910"};
            String[] aresults = data.getPrerequisites();
            for (int i = 0; i < eresults.length; i++) {
                assertEquals("prereq list", eresults[i], aresults[i].trim());
            }
            // MATH 1910 has three course-based pre-requisites and two test-based pre-requisites
            data = new Course("MATH", "1910", "202180");
            String[] e0results = {"MATH 1710", "MATH 1720", "MATH 1730", "A02 27 to 36", "S02 610 to 800", "S12B 630 to 800"};
            aresults = data.getPrerequisites();
            for (int i = 0; i < e0results.length; i++) {
                assertEquals("prereq list", e0results[i], aresults[i].trim());
            }

            //Tried Social work because pre-req does not have "( " front. Broke my prereq. Had to find a new split.
            data = new Course("SW", "4900", "202180");
            assertNull(data.getPrerequisites());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }


    }


    @Test
    public void getNoPrereqs() throws CourseNotFoundException {
        try {
            data = new Course("NURS", "4500", "202180");
            String[] aresults = data.getPrerequisites();
            assertNull("Course with no prereq", aresults);
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getNoCoursePrereqs() throws CourseNotFoundException {
        try {
            data = new Course("NURS", "4575", "202180");
            assertNull("Null course no prereq", data.getPrerequisites());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getCRH() throws CourseNotFoundException {
        try {
            data = new Course("CSC", "3500", "202180");
            assertTrue("Credit Hours", 1 == data.getCredits());
            data = new Course("CSC", "4100", "202180");
            assertTrue("Credit Hours", 3 == data.getCredits());
            data = new Course("CSC", "3300", "202180");
            assertTrue("Credit Hours", 3 == data.getCredits());


            //UNIV Course 1100 Has 0 credit hour class.
            data = new Course("UNIV", "1100", "202180");
            assertTrue("Credit Hours", 0 == data.getCredits());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }

    }

    @Test
    public void getCRHNoCourse() throws CourseNotFoundException {
        try {
            data = new Course("NURS", "4575", "202180");
            assertTrue("Credit Hours - underspecified", -1 == data.getCredits());
        }catch(CourseNotFoundException e) {
            System.out.println(e);
        }

    }

}
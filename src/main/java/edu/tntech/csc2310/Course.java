package edu.tntech.csc2310;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("SpellCheckingInspection")
public class Course {

    private static final String url = "https://ttuss1.tntech.edu/PROD/bwckctlg.p_disp_course_detail?";
    private String subject;
    private String number;
    private String title;
    private String description;
    private int credits;
    private String[] prerequisites;

    /**
     *
     * This constructor will scrape HTML from TTU's catalog entries using jsoup (if valid subject and catalog year).
     * It will grab the specific HTML class for title, and description of the catalog entries webpage.
     *
     * @param subject - Passes in the course subject code to the course constructor.
     * @param number - Passes in the course number to the course constructor.
     * @param term - Passes in the courses catalog year to the course constructor.
     * @throws CourseNotFoundException - When a course subject code does not exist in the catalog entrie webpage, it will throw a new
     *                                   exception listing the course that does not exist to the user.
     */

    public Course(String subject, String number, String term) throws CourseNotFoundException {

        /**
         * TODO: Add exception support here. This method should specify that it throws an exception
         * Modify this method so that it throws a CourseNotFoundException if the course does
         * not exist.
         */

        String subj = subject.trim().toUpperCase();
        String numb = number.trim();
        Integer trm = Integer.parseInt(term.trim());
        Integer numbTest = Integer.parseInt(numb);

        String searchUrl = url + "&cat_term_in=" + trm.toString() + "&subj_code_in=" + subj + "&crse_numb_in=" + numbTest.toString();
        try {
            Document doc = Jsoup.connect(searchUrl).get();
            Elements elements = doc.select(".nttitle");
            if (elements.size() > 0) {
                String temp = (String) elements.get(0).text();
                int index = temp.indexOf('-');
                this.title = temp.substring(index + 2);

                Elements courseDescription = doc.select(".ntdefault");
                this.description = courseDescription.get(0).text();
                this.subject = subj;
                this.number = numb;
                this.credits = (int) this.parseCRH();
            } else {
                this.subject = null;
                this.description = null;
                this.number = null;
                this.credits = -1;
                throw new CourseNotFoundException(subj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return - Will return the parsed temp variable for credit hours as a double to the course constructor.
     */
    private double parseCRH(){
        int index = this.description.indexOf("Credit hours");
        String tmp = this.description.substring(0, index-1);
        int first = tmp.lastIndexOf(" ");
        int idx = Math.max(first, 0);
        tmp = tmp.substring(idx).trim();
        return Double.parseDouble(tmp);
    }

    /**
     *
     * @return - Will return the course subject code to the course constructor.
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return - Will return the course number as a string to the course constructor.
     */
    public String getNumber() {
        return number;
    }

    /**
     *
     * @return - Will return the course title as a string to the course constructor.
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return - Will return the course description as a string to the course constructor.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Creates a flattened list of pre-requisites; removes C or D or better information,
     * as well as disjunctive normal form. All structure should be removed from the pre-requisite list.
     *
     * @return - Will return the course prerequisites as a string array to the course construcctor.
     */
    public String[] getPrerequisites() {

        String[] repls = {
                "Course or Test: ",
                "Minimum Grade of C ",
                "Minimum Grade of D ",
                "May not be taken concurrently.",
                "May be taken concurrently.",
                "(",
                ")"
                };

        String[] list = null;
        if (this.description != null) {
            int sindex = this.description.lastIndexOf("Requirements:");
            if (sindex > 0) {
                String subStr = this.description.substring(sindex + 13).trim();
                for (int i = 0; i < repls.length; i++) {
                    subStr = subStr.replace(repls[i], "");
                }
                subStr = subStr.replace("or", ",");
                subStr = subStr.replace("and", ",");
                list = subStr.split(",");
            }
        }
        return list;
    }

    /**
     *
     * @return - Will return the course credit hours to the course constructor.
     */
    public int getCredits() {
        return credits;
    }

    /**
     *
     * @return - Will return the course subject code, number, title, and description.
     */
    public String toString(){
        return subject + " " + number + " " + title + "\n" + description;
    }

    /**
     * This function states that it was never used.
     *
     * @param full - TBD
     * @return - TBD
     */
    public String toString(boolean full){
        if (full)
            return this + this.description;
        else
            return this.toString();
    }

    /**
     * This function states that it was never used.
     *
     * @param msg - TBD
     * @param vals - TBD
     */
    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));
    }

}

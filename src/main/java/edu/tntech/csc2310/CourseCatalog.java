package edu.tntech.csc2310;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseCatalog {

    private ArrayList<Course> db;
    private String catalogYear;
    private String subject;

    /**
     *
     * @return - Returns the catalog year for the list of courses to the catalog constructor.
     */
    public String getCatalogYear() {
        return catalogYear;
    }

    /**
     *
     * @return - Returns the subject code of the course to the catalog constructor.
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return - Returns array of catalog course's database to the catalog constructor.
     */
    public ArrayList<Course> getCourses(){
        return db;
    }

    /**
     * This constructor creates a catalog of courses in which the catalog gets transferred into a course array database.
     * Checks that the user submits a valid subject code, and catalog year. Also implements caching to retrieve the
     * catalog data faster.
     *
     * @param subject - Passes in the string subject code that the user inputs into the catalog constructor.
     * @param catalogYear - Passes in the catalog year that the user inputs into the catalog constructor.
     * @throws CatalogNotFoundException - When a subject code does not exist, it will throw a new exception that will display
     *                                    a message to the user that the catalog could not be found.
     */
    public CourseCatalog(String subject, String catalogYear) throws CatalogNotFoundException{

        String subj = subject.trim().toUpperCase();
        Integer trm = Integer.parseInt(catalogYear.trim());

        // TODO: Add exception handling. This method should specify that it throws an exception
        // Address the problem of subject and/or catalog year not existing

        // TODO: Implement Caching
        // Modify this method so that it only scrapes the website (either directly via getCourseNumbers
        // or indirectly by calling the Course constructor).
        // This method should check to see if a cached file exists. If so, read the file, if not
        // process as usual and save to the cached version.
        //
        this.catalogYear = trm.toString();
        this.subject = subj.toUpperCase();
        this.db = new ArrayList();

        Gson gson = new Gson();

        try {
            String filename = this.subject + "_" + this.catalogYear + ".json";
            File file = new File("src/main/resources/" + filename);
            if (file.createNewFile()) {
                ArrayList<String> list = CourseCatalog.getCourseNumbers(this.subject, this.catalogYear);
                if(list.size() > 0) {
                    try {
                        for (String s : list) {
                            Course c = new Course(this.subject, s, this.catalogYear);
                            this.db.add(c);
                        }
                    } catch (CourseNotFoundException e) {
                        System.out.println(e);
                    }
                    String jsonString = gson.toJson(db);
                    FileWriter out = new FileWriter(file);
                    out.write(jsonString);
                    out.close();
                } else {
                    file.delete();
                    this.subject = null;
                    this.catalogYear = null;
                    this.db = null;
                    throw new CatalogNotFoundException(subject);
                }

            }else {
                FileReader readIn = new FileReader(file);
                JsonReader fileIn = gson.newJsonReader(readIn);
                Course[] occurrence = gson.fromJson(fileIn, Course[].class);
                if(occurrence != null) {
                    for(Course s : occurrence) {
                        db.add(s);
                    }
                }
            }
        }catch(IOException e){

        }
    }

    /**
     *  This function checks to make sure that the course number is valid.
     *
     * @param number - Passes in a string number into the getCourse function.
     * @return - Returns the valid string number back to the catalog constructor.
     */
    public Course getCourse(String number){
        Course result = null;
        for (Course c: db){
            if (c.getNumber().equalsIgnoreCase(number)){
                result = c;
                break;
            }
        }
        return result;
    }

    /**
     *
     * @return - Will return the catalog course's database as a string.
     */
    public String toString(){
        return this.db.toString();
    }

    /**
     * This function scrape HTML TTU's catalog entries using jsoup, and will parse out the course's numbers into an array
     * named list.
     *
     * @param subject - Passes in the course's subject codes to the fucntion.
     * @param catalogYear - Passes in the course's catalog year to the function.
     * @return - Will return the course numbers as an array to the catlaog constructor.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static ArrayList<String> getCourseNumbers(String subject, String catalogYear){

        Document doc = null;
        ArrayList<String> list = new ArrayList();
        try {
            doc = Jsoup.connect("https://ttuss1.tntech.edu/PROD/bwckctlg.p_display_courses?sel_crse_strt=1000&sel_crse_end=4999&sel_subj=&sel_levl=&sel_schd=&sel_coll=&sel_divs=&sel_dept=&sel_attr="+"&term_in="+catalogYear+"&one_subj="+subject).get();
            Elements courseTitles = doc.select(".nttitle");
            for (Element title : courseTitles) {
                String line = title.text();
                Scanner scan = new Scanner(line);
                scan.useDelimiter(" ");
                scan.next();
                String crseNum = scan.next();
                list.add(crseNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
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

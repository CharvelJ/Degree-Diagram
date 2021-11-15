package edu.tntech.csc2310;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseCatalog {

    private ArrayList<Course> db;
    private String catalogYear;
    private String subject;

    public String getCatalogYear() {
        return catalogYear;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<Course> getCourses(){
        return db;
    }

    public CourseCatalog(String subject, String catalogYear) {

        String subj = subject.trim().toUpperCase();
        Integer trm = Integer.parseInt(catalogYear.trim());

        //
        // Modify this method so that it only scrapes the website (either directly via getCourseNumbers
        // or indirectly by calling the Course constructor).
        // This method should check to see if a cached file exists. If so, read the file, if not
        // process as usual and save to the cached version.
        //
        this.catalogYear = trm.toString();
        this.subject = subj.toUpperCase();
        this.db = new ArrayList();
        ArrayList<String> list = CourseCatalog.getCourseNumbers(this.subject, this.catalogYear);
        if (list.size() > 0) {
            for (String s : list) {
                Course c = new Course(this.subject, s, this.catalogYear);
                this.db.add(c);
            }
        } else {
            this.subject = null;
            this.catalogYear = null;
            this.db = null;
        }
    }

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

    public String toString(){
        return this.db.toString();
    }

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

    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));
    }

}

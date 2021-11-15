package demo;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class GSONDemo {

   public static void main(String[] args){

       Gson gson = new Gson();
       String key = "F419E6B3-FE95-444F-B5EE-D90BA7C71751";
       String subject = "CSC";

       try {
           URL url = new URL("https://portapi.tntech.edu/express/api/unprotected/getCourseInfoByAPIKey.php?Key=" + key + "&Term=202180&Subject=" + subject);
           BufferedReader in =new BufferedReader(new InputStreamReader(url.openStream()));
           JsonReader jr = gson.newJsonReader(in);
           Instance[] instance = gson.fromJson(jr, Instance[].class);
           // instance is an array of Instance objects
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

   }

}

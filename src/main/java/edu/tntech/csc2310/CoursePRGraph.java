package edu.tntech.csc2310;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

@RestController
public class CoursePRGraph {


    private final AtomicLong counter = new AtomicLong();

    /**
     * prerequisites service returns a json array formatted as [[ from, to, weight], *]. Java objects are
     * automatically serialized into JSON objects.
     * @param prefix
     * @return
     */
    @GetMapping("/prerequisites")
    public CoursePR prerequisites(@RequestParam(value = "prefix", defaultValue = "CSC") String prefix) {
        return new CoursePR(counter.incrementAndGet(), fetchData(prefix));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursePRGraph.class.getName());

    /**
     * fetchData demonstrates how to read file data on the server from the resources directory
     * @return
     */
    public ArrayList<HashMap<String, String>> fetchData(String prefix){

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:test.csv");

            Scanner s = new Scanner(file);
            s.useDelimiter(",");
            while (s.hasNextLine()){
                String line = s.nextLine();
                Scanner lineScan = new Scanner(line);
                lineScan.useDelimiter(",");
                String from = lineScan.next();
                String to = lineScan.next();
                String weight = lineScan.next();
                String title = lineScan.next();
                HashMap<String, String> map = new HashMap<>();
                map.put("from", from);
                map.put("to", to);
                map.put("weight", weight);
                map.put("title", title);
                list.add(map);
            }
            s.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return list;
    }

}

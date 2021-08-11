package edu.tntech.csc2310;

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
public class CoursePRController {


    private final AtomicLong counter = new AtomicLong();

    /**
     * prerequisites service returns a json array formatted as [[ from, to, weight], *]
     * @param prefix
     * @return
     */
    @GetMapping("/prerequisites")
    public CoursePR prerequisites(@RequestParam(value = "prefix", defaultValue = "CSC") String prefix) {
        String text = "%s," + this.fetchData();
        return new CoursePR(counter.incrementAndGet(), String.format(text,prefix));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursePRController.class.getName());

    /**
     * fetchData demonstrates how to read file data on the server from the resources directory
     * @return
     */
    public String fetchData(){

        String result = new String();
        try {
            File file = ResourceUtils.getFile("classpath:test.csv");
            Scanner s = new Scanner(file);
            while (s.hasNext()){
                result = result + s.next() + ",";
            }
            result = result + "end";
            s.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

}

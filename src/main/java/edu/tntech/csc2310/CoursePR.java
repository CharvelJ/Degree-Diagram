package edu.tntech.csc2310;

import java.util.ArrayList;
import java.util.HashMap;

public class CoursePR {

    private final long id;

    private final ArrayList<HashMap<String, String>> adjlist;

    public CoursePR(long id, ArrayList<HashMap<String, String>> adjlist) {
        this.id = id;
        this.adjlist = adjlist;
    }

    public ArrayList<HashMap<String, String>> getAdjacencyList() {
        return adjlist;
    }

    public long getId() {
        return id;
    }

}

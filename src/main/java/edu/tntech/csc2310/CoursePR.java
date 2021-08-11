package edu.tntech.csc2310;

public class CoursePR {

    private final long id;
    private final String content;

    public CoursePR(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

}

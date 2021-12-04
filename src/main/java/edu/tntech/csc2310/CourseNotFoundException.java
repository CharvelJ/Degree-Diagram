package edu.tntech.csc2310;

public class CourseNotFoundException extends Exception {

    private String message;

    public CourseNotFoundException() {
        super();
    }

    /**
     *
     * @param message - Passes in the subject code from Course class to CourseNotFoundException function.
     */
    public CourseNotFoundException(String message) {
        super(message);
        this.message = "This course was not able to be found: " + message;
    }

    /**
     *
     * @return - This will the return the instance of the string message.
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     *
     * @return - This will return a comment plus the message wrote out from getMessage function.
     */
    @Override
    public String toString() {
        return "Course error >>> " + getMessage();
    }
}

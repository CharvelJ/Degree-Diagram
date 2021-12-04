package edu.tntech.csc2310;

public class CatalogNotFoundException extends Exception{

    private String message;

    public CatalogNotFoundException() {
        super();
    }

    /**
     *
     * @param message - Passes in the subject code from CourseCatalog class to CatalogNotFoundException function.
     */
    public CatalogNotFoundException(String message) {

        super(message);
        this.message = "This course catalog was not able to be found: " + message;

    }

    /**
     *
     * @return -
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     *
     * @return -
     */
    @Override
    public String toString() {
        return "Course catalog error >>> " + getMessage();
    }
}

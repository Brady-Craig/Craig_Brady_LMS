package ocLMS;

/*
 * Brady Craig, Software Development 1, 03-02-2024
 * 
 * Book Class
 * 
 * Used to give books identifiable attributes such as barcode, title, author and checked out/in status.
 * Used to store book data in LMS.
 */
public class Book {
    private int barcode;
    private String title;
    private String author;
    private boolean checkedOut;

    public Book(int id, String title, String author) {
        this.barcode = id;
        this.title = title;
        this.author = author;
        this.checkedOut = false; 
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }


    public String getBookTitle() {
        return title;
    }

    public void setBookTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return author;
    }

    public void setAuthorName(String author) {
        this.author = author;
    }
    
    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    @Override
    public String toString() {
        return "Barcode: " + barcode + "," + "Title: " + title + "," + "Author: " + author + "," + "Status: "  + checkedOut;
    }
}
package application;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Brady Craig, Software Development 1, 03-23-2024
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
    private LocalDate dueDate;

    public Book(int id, String title, String author) {
        this.barcode = id;
        this.title = title;
        this.author = author;
        this.checkedOut = false;
        this.dueDate = null; // Default value
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
    
    public void setDueDate(LocalDate localDate) {
        this.dueDate = LocalDate.now().plusDays(7);
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
    	String status = checkedOut ? "Checked Out" : "Checked In";
    	String dueDateStr = (dueDate != null) ? dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) : "N/A";
        return "Barcode: " + barcode + ", " + " Title: " + title + ", " + " Author: " + author + ", " + " Status: "  + status + ", " + "Due Date: " + dueDateStr;
    }

}
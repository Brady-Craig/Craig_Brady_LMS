package application;

import java.time.LocalDate;

import javafx.beans.property.*;

/*
 * Brady Craig, Software Development 1, 04-14-2024
 * 
 * Book Class
 * 
 * Used to give books identifiable property attributes such as barcode, title, author, genre and checked out/in status. 
 * Updated book class to utilize Property data types so TableView could be used in the GUI.
 * Used to store book data in LMS database.
 */


/**
 * Represents a book with identifiable properties such as barcode, title, author, genre, and checked out/in status.
 * This class utilizes Property data types to enable integration with JavaFX TableView.
 * Used to store book data in the Library Management System (LMS) database.
 */
public class Book {
    private final IntegerProperty barcode;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty genre;
    private final BooleanProperty checkedOut;
    private final ObjectProperty<LocalDate> dueDate;

    /**
     * Constructs a new Book object with the specified properties.
     *
     * @param barcode    The barcode of the book.
     * @param title      The title of the book.
     * @param author     The author of the book.
     * @param genre      The genre of the book.
     * @param checkedOut The checked out status of the book.
     * @param dueDate    The due date of the book if it is checked out, otherwise null.
     */
    public Book(int barcode, String title, String author, String genre, boolean checkedOut, LocalDate dueDate) {
        this.barcode = new SimpleIntegerProperty(barcode);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.checkedOut = new SimpleBooleanProperty(checkedOut);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
    }
    
    /**
     * Gets the barcode property of the book.
     *
     * @return The barcode property.
     */
    public IntegerProperty barcodeProperty() {
        return barcode;
    }

    /**
     * Gets the value of the barcode property.
     *
     * @return The barcode value.
     */
    public int getBarcode() {
        return barcode.get();
    }
    /**
     * Sets the value of the barcode property.
     *
     * @param barcode The barcode value to set.
     */
    public void setBarcode(int barcode) {
        this.barcode.set(barcode);
    }
    
    /**
     * Gets the title property of the book.
     *
     * @return The title property.
     */
    public StringProperty titleProperty() {
        return title;
    }
    
    /**
     * Gets the value of the title property.
     *
     * @return The title value.
     */
    public String getBookTitle() {
        return title.get();
    }
    
    /**
     * Sets the value of the title property.
     *
     * @param barcode The title value to set.
     */

    public void setBookTitle(String title) {
        this.title.set(title);
    }
    
    /**
     * Gets the author property of the book.
     *
     * @return The author property.
     */
    public StringProperty authorProperty() {
        return author;
    }

    /**
     * Gets the value of the author property.
     *
     * @return The author value.
     */
    public String getAuthorName() {
        return author.get();
    }
    
    /**
     * Sets the value of the author property.
     *
     * @param barcode The author value to set.
     */
    public void setAuthorName(String author) {
        this.author.set(author);
    }
    
    /**
     * Gets the genre property of the book.
     *
     * @return The genre property.
     */
    public StringProperty genreProperty() {
        return genre;
    }
    
    /**
     * Gets the value of the genre property.
     *
     * @return The genre value.
     */
    public String getGenre() {
        return genre.get();
    }
    
    /**
     * Sets the value of the genre property.
     *
     * @param barcode The genre value to set.
     */
    public void setGenre(String genre) {
        this.genre.set(genre);
    }
    /**
     * Gets the checkedOut property of the book.
     *
     * @return The checkedOut property.
     */
    public BooleanProperty checkedOutProperty() {
        return checkedOut;
    }
    /**
     * Gets the value of the checkedOut property.
     *
     * @return The checkoOut value.
     */
    public boolean isCheckedOut() {
        return checkedOut.get();
    }
    
    /**
     * Sets the value of the checkedOut property.
     *
     * @param barcode The checkedOut value to set.
     */
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut.set(checkedOut);
    }
    /**
     * Gets the dueDate property of the book.
     *
     * @return The dueDate property.
     */
    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }
    /**
     * Gets the value of the dueDate property.
     *
     * @return The dueDate value.
     */
    public LocalDate getDueDate() {
        return dueDate.get();
    }
    
    /**
     * Sets the value of the dueDate property.
     *
     * @param barcode The dueDate value to set.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }
}
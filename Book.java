package application;

import java.time.LocalDate;

import javafx.beans.property.*;

/*
 * Brady Craig, Software Development 1, 04-07-2024
 * 
 * Book Class
 * 
 * Used to give books identifiable property attributes such as barcode, title, author, genre and checked out/in status. 
 * Updated book class to utilize Property data types so TableView could be used in the GUI.
 * Used to store book data in LMS database.
 */
public class Book {
    private final IntegerProperty barcode;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty genre;
    private final BooleanProperty checkedOut;
    private final ObjectProperty<LocalDate> dueDate;

    public Book(int barcode, String title, String author, String genre, boolean checkedOut, LocalDate dueDate) {
        this.barcode = new SimpleIntegerProperty(barcode);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.checkedOut = new SimpleBooleanProperty(checkedOut);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
    }

    public IntegerProperty barcodeProperty() {
        return barcode;
    }

    public int getBarcode() {
        return barcode.get();
    }

    public void setBarcode(int barcode) {
        this.barcode.set(barcode);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getBookTitle() {
        return title.get();
    }

    public void setBookTitle(String title) {
        this.title.set(title);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getAuthorName() {
        return author.get();
    }

    public void setAuthorName(String author) {
        this.author.set(author);
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public BooleanProperty checkedOutProperty() {
        return checkedOut;
    }

    public boolean isCheckedOut() {
        return checkedOut.get();
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut.set(checkedOut);
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }
}
package application;
/*
 * Brady Craig, Software Development 1, 03-23-2024
 * 
 * LibraryManagementSystemTest Class
 * 
 * JUnit Tests for LMS application. Tests adding a book by file, removing a book by barcode and title, checks in/out books and updates status.
 */

/**
 * The LibraryManagementSystemTest class contains JUnit tests for the Library Management System (LMS) application.
 * These tests include adding a book by file, removing a book by barcode and title, checking books in and out, and updating book status.
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LibraryManagementSystemTest {
    private LibraryManagementSystem lms;
    /**
     * Sets up the test environment by initializing the LibraryManagementSystem with test books.
     */
    @Before
    public void setUp() {
        // Initialize LibraryManagementSystem with test books
        List<Book> testBooks = TextFileReader.readBookTextFile("/Users/bradycraig/Desktop/NewBookData.rtf"); //update file pathway for your book data
        lms = new LibraryManagementSystem(testBooks);
    }
    /**
     * Tests the removal of a book by its barcode.
     */
    @Test
    public void testRemoveBookByBarcode() {
        int barcodeToRemove = 1; // use a barcode that matches your book data file
        lms.deleteBookByBarcode(barcodeToRemove);

        
        List<Book> booksAfterRemoval = lms.listBookCollection();

        
        for (Book book : booksAfterRemoval) {
            assertFalse(book.getBarcode() == barcodeToRemove);
        }
    }
    /**
     * Tests the removal of a book by its title.
     */
    @Test
    public void testRemoveBookByTitle() {
        String titleToRemove = "1984"; // use a Title that matches one from your book data file.
        lms.deleteBookByTitle(titleToRemove);

        List<Book> booksAfterRemoval = lms.listBookCollection();

        for (Book book : booksAfterRemoval) {
            
            assertFalse(book.getBookTitle().equals(titleToRemove));
        }
    }
    /**
     * Tests the check-out process for a book.
     */
    @Test
    public void testCheckOutBook() {
        String titleToCheckOut = "The Martian"; // use a Title that matches one from your book data file.
        lms.checkOutBook(titleToCheckOut);

        Book checkedOutBook = getBookByTitle(titleToCheckOut);

        assertNotNull(checkedOutBook);
        assertTrue(checkedOutBook.isCheckedOut());
        assertNotNull(checkedOutBook.getDueDate());
    }
    /**
     * Tests the check-in process for a book.
     */
    @Test
    public void testCheckInBook() {
        String titleToCheckIn = "The Martian"; // use a Title that matches one from your book data file.
        lms.checkInBook(titleToCheckIn);

        Book checkedInBook = getBookByTitle(titleToCheckIn);

        assertNotNull(checkedInBook);
        assertFalse(checkedInBook.isCheckedOut());
        assertNull(checkedInBook.getDueDate());
    }
    /**
     * Helper method to retrieve a book by its title from the book collection.
     *
     * @param title The title of the book to retrieve.
     * @return The Book object with the specified title, or null if not found.
     */
    private Book getBookByTitle(String title) {
        for (Book book : lms.listBookCollection()) {
            if (book.getBookTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }
}

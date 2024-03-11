package ocLMS;

import java.util.List;
import java.time.LocalDate;
import java.util.Iterator;

/*
 * Brady Craig, Software Development 1, 03-09-2024
 * 
 * LibraryManagementSystem Class
 * 
 * Helps with managing books in the LMS by listing all books in collection, deleting books from collection by barcode and title, adding books by text files, checking books in and out.
 */
public class LibraryManagementSystem {
    private List<Book> bookCollection;

    public LibraryManagementSystem(List<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }
    /*
     * addBooksByFile Method
     * 
     * Using a specific text file, books are added and checked for duplicates.
     * Utilizes filePath to determine specific text file.
     */
    public void addBooksByFile(String filePath) {
        List<Book> newBooks = TextFileReader.readBookTextFile(filePath);
        //for-each loop checking for duplicates
        for (Book newBook : newBooks) {
            if (!duplicateBookCheck(newBook)) {
                bookCollection.add(newBook);
                System.out.println("Book added to the database: " + newBook + "\n");
            } else {
                System.out.println("Book with Barcode " + newBook.getBarcode() + " already exists in the database.");
            }
        }
    }
    /*
     * deleteBookByBarcode Method
     * 
     * Delete books from collection using a book's Barcode.
     * iterator is used to search collection bookBarcodes.
     */
    public void deleteBookByBarcode(int barcode) {
        Iterator<Book> iterator = bookCollection.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBarcode() == barcode) {
                iterator.remove();
                System.out.println("Book with barcode " + barcode + " removed from the database.");
                return;
            }
        }
        System.out.println("Book with barcode " + barcode + " not found in the collection.");
    }
    /*
     * deleteBookByTitle Method
     * 
     * Using the BookTitle to delete a book from the collection.
     * Checks book collection to make sure the book is in the collection.
     */
    
    public void deleteBookByTitle(String title) {
        Iterator<Book> iterator = bookCollection.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookTitle().equals(title)) {
                iterator.remove();
                System.out.println("Book with title \"" + title + "\" removed from the database.");
                return;
            }
        }
        System.out.println("Book with title \"" + title + "\" not found in the collection.");
    }
    /*
     * checkOutBook Method
     * 
     * Uses BookTitle to check out a book.
     * Also checks book collection to make sure book is in the collection.
     */
    
    public void checkOutBook(String title) {
        for (Book book : bookCollection) {
            if (book.getBookTitle().equals(title)) {
                if (!book.isCheckedOut()) {
                    book.setCheckedOut(true);
                    book.setDueDate(LocalDate.now().plusDays(7));
                    System.out.println("Book \"" + title + "\" checked out successfully. Due date: " + book.getDueDate());
                } else {
                    System.out.println("Book \"" + title + "\" is already checked out.");
                }
                return;
            }
        }
        System.out.println("Book with title \"" + title + "\" not found in the collection.");
    }
    
    
    /*
     * checkInBook Method
     * 
     * Uses BookTitle to check in a book that was previously checked out.
     * Also checks book collection to make sure book is in the collection.
     */ 
    
    public void checkInBook(String title) {
        for (Book book : bookCollection) {
            if (book.getBookTitle().equals(title)) {
                if (book.isCheckedOut()) {
                    book.setCheckedOut(false);
                    System.out.println("Book \"" + title + "\" checked in successfully.");
                } else {
                    System.out.println("Book \"" + title + "\" is not checked out.");
                }
                return;
            }
        }
        System.out.println("Book with title \"" + title + "\" not found in the collection.");
    }

   
     /*
     * listBookCollection Method
     * 
     * Lists collection of books currently in the LMS program.
     * Utilizes filePath to determine specific text file.
     */
    	
    public List<Book> listBookCollection() {
        for (Book book : bookCollection) {
            System.out.println(book);
        }
		return bookCollection;
    }
    /*
     * duplicateBookCheck Method
     * 
     * Checks current book collection for duplicates when adding a new book text file.
     * Skips duplicate books and only adds new books.
     */
    private boolean duplicateBookCheck(Book newBook) {
        for (Book existingBook : bookCollection) {
            if (existingBook.getBarcode() == newBook.getBarcode()) {
                return true;
            }
        }
        return false;
    }
}
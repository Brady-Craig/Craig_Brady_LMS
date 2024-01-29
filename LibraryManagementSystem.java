package ocLMS;

import java.util.List;
import java.util.Iterator;

/*
 * Brady Craig, Software Development 1, 01-27-2024
 * 
 * LibraryManagementSystem Class
 * 
 * Helps with managing books in the LMS by listing all books in collection, deleting books from collection by ID, and adding books by text files.
 * 
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
                System.out.println("Book with ID " + newBook.getBookId() + " already exists in the database.");
            }
        }
    }
    /*
     * deleteBookById Method
     * 
     * Delete books from collection using a book's ID.
     * iterator is used to search collection bookIDs
     */
    public void deleteBookById(int bookId) {
    	//maintains collection order while locating Book ID for removal
        Iterator<Book> iterator = bookCollection.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookId() == bookId) {
                iterator.remove();
                System.out.println("Book(s) removed from the database.");
                return;
            }
        }
        //print if book ID not found
        System.out.println("Book with ID " + bookId + " not found in the collection.");
    }
    /*
     * listBookCollection Method
     * 
     * Lists collection of books currently in the LMS program.
     * Utilizes filePath to determine specific text file.
     */
    	
    public void listBookCollection() {
        for (Book book : bookCollection) {
            System.out.println(book);
        }
    }
    /*
     * duplicateBookCheck Method
     * 
     * Checks current book collection for duplicates when adding a new book text file.
     * Skips duplicate books and only adds new books.
     */
    private boolean duplicateBookCheck(Book newBook) {
        for (Book existingBook : bookCollection) {
            if (existingBook.getBookId() == newBook.getBookId()) {
                return true;
            }
        }
        return false;
    }
}
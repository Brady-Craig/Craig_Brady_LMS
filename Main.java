package ocLMS;

import java.util.Scanner;
import java.util.ArrayList;

/*
 * Brady Craig, Software Development 1, 01-27-2024
 * 
 * Main Class
 * 
 * LMS entry point and presents operation menu to user.
 * Initializes LMS program.
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> bookCollection = new ArrayList<>();
        LibraryManagementSystem lms = new LibraryManagementSystem(bookCollection);

        int choice;
        // do while loop to keep LMS running until user chooses to exit
        do {
            System.out.println("\nMenu:\n");
            System.out.println("1. Use a text file to add new books");
            System.out.println("2. Input a book ID to remove a book");
            System.out.println("3. List all books in the collection");
            System.out.println("4. Exit the Library Management System");
            System.out.print("\nEnter your selection:\n ");
            choice = scanner.nextInt();
            scanner.nextLine(); //program runs based on user's selection

            switch (choice) {
                case 1:
                    // Add books to collection using a text file
                    System.out.print("Enter the path of the text file with book data: ");
                    String filePath = scanner.nextLine();
                    lms.addBooksByFile(filePath);
                    break;
                case 2:
                    // Remove a book by  book ID
                    System.out.print("Enter the ID of the book to remove: ");
                    int removeBookById = scanner.nextInt();
                    lms.deleteBookById(removeBookById);
                    break;
                case 3:
                    // List all books in the collection
                    System.out.println("List of all books in the collection:");
                    lms.listBookCollection();
                    break;
                case 4:
                    System.out.println("Exiting the Library Management System...");
                    break;
                default:
                    System.out.println("Invalid selection. Please enter a number between 1 and 4.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}

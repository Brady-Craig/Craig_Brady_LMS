package application;

import java.util.Scanner;
import java.util.ArrayList;

/*
 * Brady Craig, Software Development 1, 03-23-2024
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
            System.out.println("1. Use a text file to add new books.");
            System.out.println("2. Input a book barcode number to remove a book.");
            System.out.println("3. Input a title to remove a book.");
            System.out.println("4. Input a title to check out a book.");
            System.out.println("5. Input a title to check in a book.");
            System.out.println("6. List all books in the collection.");
            System.out.println("7. Exit the Library Management System.");
            System.out.print("\nEnter your selection:\n ");
            choice = scanner.nextInt();
            scanner.nextLine(); //program runs based on user's selection

            switch (choice) {
            case 1:
                // Add books from a user's text file
                System.out.print("Enter the path of the text file with book data: ");
                String filePath = scanner.nextLine();
                lms.addBooksByFile(filePath);
                break;
            case 2:
                // Remove a book by barcode
                removeBookByBarcode(lms, scanner);
                break;
            case 3:
                // Remove a book by title
                removeBookByTitle(lms, scanner);
                break;
            case 4:
                // Check out a book
                checkOutBook(lms, scanner);
                break;
            case 5:
                // Check in a book
                checkInBook(lms, scanner);
                break;
            case 6:
                // List all books in the collection
                System.out.println("List of all books in the collection:");
                lms.listBookCollection();
                break;
            case 7:
                System.out.println("Exiting the Library Management System...");
                break;
            default:
                System.out.println("Invalid selection. Please enter a number between 1 and 7.");
                break;
        }
    } while (choice != 7);

    scanner.close();
    }

	 private static void removeBookByBarcode(LibraryManagementSystem lms, Scanner scanner) {
        System.out.print("Enter the barcode number to remove a book: ");
        int barcodeToRemove = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Remove a book by barcode
        lms.deleteBookByBarcode(barcodeToRemove);

        // Print the updated database
        System.out.println("\nPrinting the updated database...\n");
        lms.listBookCollection();
    }

    public static void removeBookByTitle(LibraryManagementSystem lms, Scanner scanner) {
        System.out.print("Enter the title to remove a book: ");
        String titleToRemove = scanner.nextLine();

        // Remove a book by title
        lms.deleteBookByTitle(titleToRemove);

        // Print the updated database
        System.out.println("\nPrinting the updated database...\n");
        lms.listBookCollection();
    }

    public static void checkOutBook(LibraryManagementSystem lms, Scanner scanner) {
        System.out.print("Enter the title to check out a book: ");
        String titleToCheckOut = scanner.nextLine();

        // Check out a book
        lms.checkOutBook(titleToCheckOut);

        // Print the updated database
        System.out.println("\nPrinting the updated database...\n");
        lms.listBookCollection();
    }

    public static void checkInBook(LibraryManagementSystem lms, Scanner scanner) {
        System.out.print("Enter the title to check in a book: ");
        String titleToCheckIn = scanner.nextLine();

        // Check in a book
        lms.checkInBook(titleToCheckIn);

        // Print the updated database
        System.out.println("\nPrinting the updated database...\n");
        lms.listBookCollection();
    }
	}
  

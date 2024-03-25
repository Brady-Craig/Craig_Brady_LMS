package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Brady Craig, Software Development 1, 03-23-2024
 * 
 * TextFileReader Class
 * 
 * Used to handle reading book data from text files and create book objects from text file data.
 * Helps load book data in LMS
 */


public class TextFileReader {
    public static List<Book> readBookTextFile(String filePath) {
        List<Book> books = new ArrayList<>();
        //try will close BufferedReader even if there is an exception
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //while loop to continue reading lines until empty line
            while ((line = reader.readLine()) != null) {
                //split line into 3 elements and check that book format is correct
            	String[] elements = line.split(",");
                if (elements.length == 3) {
                    int id = Integer.parseInt(elements[0]);
                    String title = elements[1];
                    String author = elements[2];
                    Book book = new Book(id, title, author);
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}
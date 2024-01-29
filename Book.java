package ocLMS;

/*
 * Brady Craig, Software Development 1, 01-27-2024
 * 
 * Book Class
 * 
 * Used to give books identifiable attributes such as id, title, and author.
 * Used to store book data in LMS.
 */
public class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return id;
    }

    public void setBookId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return id + "," + title + "," + author;
    }
}
package application;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;

/*
 * Brady Craig, Software Development 1, 04-07-2024
 *
 * LibraryManagementSystemGUI Class
 *
 * Creates a JavaFX GUI/interface with OC_LMS database for the OC LMS application. Allows user to list all books in database, delete books from database by barcode and title, adds books by text files, checks books in and out.
 */
public class LibraryManagementSystemGUI extends Application {
    private Connection connection;
    private TableView<Book> bookTableView;

    @Override
    public void start(Stage primaryStage) {
        connectToDatabase(); // Connect to SQLite database
        createTable(); 

        Label fileLabel = new Label("File Name:");
        TextField fileTextField = new TextField();
        Button addBtn = new Button("Add Books");
        Button printBtn = new Button("Print Database");
        Label barcodeLabel = new Label("Barcode:");
        TextField barcodeTextField = new TextField();
        Button RemoveBarcodeBtn = new Button("Remove by Barcode");
        Label titleLabel = new Label("Title:");
        TextField titleTextField = new TextField();
        Button removeTitleBtn = new Button("Remove by Title");
        Label checkoutLabel = new Label("Check Out:");
        TextField checkoutTextField = new TextField();
        Button checkoutBtn = new Button("Check Out");
        Label checkinLabel = new Label("Check In:");
        TextField checkinTextField = new TextField();
        Button checkinBtn = new Button("Check In");
        Button exitBtn = new Button("Exit");
        
        //Use TableView to display database data in a formatted table
        bookTableView = new TableView<>();
        bookTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Book, Integer> barcodeColumn = new TableColumn<>("Barcode");
        barcodeColumn.setCellValueFactory(data -> data.getValue().barcodeProperty().asObject());
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(data -> data.getValue().authorProperty());
        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(data -> data.getValue().genreProperty());
        TableColumn<Book, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data -> data.getValue().checkedOutProperty().asString());
        TableColumn<Book, LocalDate> dueDateColumn = new TableColumn<>("Due Date");
        dueDateColumn.setCellValueFactory(data -> data.getValue().dueDateProperty());
        bookTableView.getColumns().addAll(barcodeColumn, titleColumn, authorColumn, genreColumn, statusColumn, dueDateColumn);


        String backgroundColor = "-fx-background-color: #055db0;";
        String buttonColor = "-fx-background-color: #607D8B; -fx-text-fill: white;";
        String labelStyle = "-fx-text-fill: white;";

        fileLabel.setStyle(labelStyle);
        barcodeLabel.setStyle(labelStyle);
        titleLabel.setStyle(labelStyle);
        checkoutLabel.setStyle(labelStyle);
        checkinLabel.setStyle(labelStyle);

        addBtn.setStyle(buttonColor);
        printBtn.setStyle(buttonColor);
        RemoveBarcodeBtn.setStyle(buttonColor);
        removeTitleBtn.setStyle(buttonColor);
        checkoutBtn.setStyle(buttonColor);
        checkinBtn.setStyle(buttonColor);
        exitBtn.setStyle(buttonColor);

        addBtn.setOnAction(e -> {
            String fileName = fileTextField.getText();
            addBooksByFile(fileName);

        });

       
        printBtn.setOnAction(e -> {
            updateBookTableView();
        });

        RemoveBarcodeBtn.setOnAction(e -> {
            int barcode = Integer.parseInt(barcodeTextField.getText());
            deleteBookByBarcode(barcode);

        });

        removeTitleBtn.setOnAction(e -> {
            String title = titleTextField.getText();
            deleteBookByTitle(title);

        });

        checkoutBtn.setOnAction(e -> {
            int barcode = Integer.parseInt(checkoutTextField.getText());
            checkOutBook(barcode);

        });

        checkinBtn.setOnAction(e -> {
            int barcode = Integer.parseInt(checkinTextField.getText());
            checkInBook(barcode);

        });

        exitBtn.setOnAction(e -> primaryStage.close());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setStyle(backgroundColor);

        grid.add(fileLabel, 0, 0);
        grid.add(fileTextField, 1, 0);
        grid.add(addBtn, 2, 0);
        grid.add(printBtn, 3, 0);
        grid.add(barcodeLabel, 0, 1);
        grid.add(barcodeTextField, 1, 1);
        grid.add(RemoveBarcodeBtn, 2, 1);
        grid.add(titleLabel, 0, 2);
        grid.add(titleTextField, 1, 2);
        grid.add(removeTitleBtn, 2, 2);
        grid.add(checkoutLabel, 0, 3);
        grid.add(checkoutTextField, 1, 3);
        grid.add(checkoutBtn, 2, 3);
        grid.add(checkinLabel, 0, 4);
        grid.add(checkinTextField, 1, 4);
        grid.add(checkinBtn, 2, 4);
        grid.add(exitBtn, 1, 5);
        grid.add(bookTableView, 0, 6, 4, 1);

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Orange County Library Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void connectToDatabase() {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // SQLite connection string
            String url = "jdbc:sqlite:/Users/bradycraig/Desktop/sqlite/db/OC_LMS.sqlite";

            // Create a connection to the database
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to SQLite database.");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS books (\n"
                + "    barcode INTEGER PRIMARY KEY,\n"
                + "    title TEXT NOT NULL,\n"
                + "    author TEXT NOT NULL,\n"
                + "    genre TEXT,\n"
                + "    status TEXT NOT NULL,\n"
                + "    due_date DATE\n"
                + ");";

        try (Statement statement = connection.createStatement()) {
            // Create books table
            statement.execute(sql);
            System.out.println("Books table created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addBooksByFile(String fileName) {
        // Decide on what to do with this section
    }

    private void deleteBookByBarcode(int barcode) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE barcode = ?");
            preparedStatement.setInt(1, barcode);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book with barcode " + barcode + " removed successfully.");
                // Update UI to reflect the deletion, such as refreshing the TableView
                updateBookTableView();
            } else {
                System.out.println("No book found with barcode " + barcode + ".");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Delete Book by barcode
    private void deleteBookByTitle(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE title = ?");
            preparedStatement.setString(1, title);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book with title \"" + title + "\" removed successfully.");
                // Update UI to reflect the deletion, such as refreshing the TableView
                updateBookTableView();
            } else {
                System.out.println("No book found with title \"" + title + "\".");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Check-Out Book by Barcode
    private void checkOutBook(int barcode) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books SET status = ?, due_date = ? WHERE barcode = ?");
            preparedStatement.setString(1, "Checked Out");
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now().plusDays(7))); // Assuming the due date is set to 7 days from today
            preparedStatement.setInt(3, barcode);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book with barcode " + barcode + " checked out successfully.");
                // Update UI to reflect the check-out, such as refreshing the TableView
                updateBookTableView();
            } else {
                System.out.println("No book found with barcode " + barcode + ".");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Check-In Book by Barcode
    private void checkInBook(int barcode) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books SET status = ?, due_date = NULL WHERE barcode = ?");
            preparedStatement.setString(1, "Checked In");
            preparedStatement.setInt(2, barcode);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book with barcode " + barcode + " checked in successfully.");
                // Update UI to reflect the check-in, such as refreshing the TableView
                updateBookTableView();
            } else {
                System.out.println("No book found with barcode " + barcode + ".");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Update Book data in GUI table/Database
    private void updateBookTableView() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            ObservableList<Book> bookList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int barcode = resultSet.getInt("barcode");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                boolean checkedOut = resultSet.getString("status").equals("Checked Out");
                Date dueDateSQL = resultSet.getDate("due_date");
                LocalDate dueDate = (dueDateSQL != null) ? dueDateSQL.toLocalDate() : null;

                bookList.add(new Book(barcode, title, author, genre, checkedOut, dueDate));
            }
            bookTableView.setItems(bookList);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

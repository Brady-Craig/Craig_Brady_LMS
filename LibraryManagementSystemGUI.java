package application;

import javafx.application.Application;
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
import java.util.ArrayList;
import java.util.List;

/*
 * Brady Craig, Software Development 1, 03-23-2024
 * 
 * LibraryManagementSystemGUI Class
 * 
 * Creates a JavaFX GUI for the OC LMS. Allows use to list all books in collection, delete books from collection by barcode and title, adds books by text files, checks books in and out.
 */
public class LibraryManagementSystemGUI extends Application {
    private LibraryManagementSystem lms;
    private TextArea bookCollectionTextArea;

    @Override
    public void start(Stage primaryStage) {

        List<Book> bookCollection = new ArrayList<>();

        // Initialize LibraryManagementSystem with book collection
        lms = new LibraryManagementSystem(bookCollection);

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
        
     // Create TextArea for displaying book collection in GUI
        bookCollectionTextArea = new TextArea();
        bookCollectionTextArea.setEditable(false);
        bookCollectionTextArea.setWrapText(true);
        
        
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

     // Button Action handlers
        addBtn.setOnAction(e -> {
            String fileName = fileTextField.getText();
            lms.addBooksByFile(fileName);
            
        });

        printBtn.setOnAction(e -> {
            updateBookCollectionTextArea();
        });

        RemoveBarcodeBtn.setOnAction(e -> {
            int barcode = Integer.parseInt(barcodeTextField.getText());
            lms.deleteBookByBarcode(barcode);
           
        });

        removeTitleBtn.setOnAction(e -> {
            String title = titleTextField.getText();
            lms.deleteBookByTitle(title);
            
        });

        checkoutBtn.setOnAction(e -> {
            String title = checkoutTextField.getText();
            lms.checkOutBook(title);
            
        });

        checkinBtn.setOnAction(e -> {
            String title = checkinTextField.getText();
            lms.checkInBook(title);
           
        });

        exitBtn.setOnAction(e -> primaryStage.close());

        // Set Grid layout for the GUI
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setStyle(backgroundColor);
        
        // Grid Components
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
        grid.add(bookCollectionTextArea, 0, 6, 4, 1);

        // Set scene and show stage
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Orange County Library Management System");
        primaryStage.show();
    }
   
    public static void main(String[] args) {
        launch(args);
    }
    private void updateBookCollectionTextArea() {
        bookCollectionTextArea.setText(formatBookList(lms.listBookCollection()));
    }

    private String formatBookList(List<Book> bookList) {
        StringBuilder builder = new StringBuilder();
        builder.append("Book Collection:\n");
        for (Book book : bookList) {
            builder.append(book.toString()).append("\n");
        }
        return builder.toString();
    }
}
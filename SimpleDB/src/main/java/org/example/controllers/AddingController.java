package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import org.example.database.Driver;

public class AddingController {
    public static final Driver driver = BeginingController.getDriver();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location ;

    @FXML
    private TextField authorNameField;

    @FXML
    private TextField bookTitleField;

    @FXML
    private Button addButton;

    @FXML
    private Button findButton;

    @FXML
    void initialize() {
        Connection connection = driver.getConnection();
        FXMLLoader loader = new FXMLLoader(  );
        addButton.setOnAction( event -> {
            try {
                Statement statement = connection.createStatement();
                String authorName = getAuthorName();
                String bookTitle = getBookTitle();
                String resultAuthorName = "";
                String resultBookTitle = "";
                ResultSet resultSetAuthor = statement.executeQuery( "select * from authors where author = '" + authorName + "'" );
                while (resultSetAuthor.next()){
                    resultAuthorName = resultSetAuthor.getString( 2 ).trim().toLowerCase();
                }
                if( !resultAuthorName.equals( authorName ) ){
                    statement.executeUpdate( "insert into authors (author) values ('"+ authorName + "')" );
                    resultAuthorName = "";
                }
                ResultSet resultSetBook = statement.executeQuery( "select * from books where book_title = '" + bookTitle + "'" );
                while (resultSetBook.next()){
                    resultBookTitle = resultSetBook.getString( 2 ).trim().toLowerCase();
                }
                if( !resultBookTitle.equals( bookTitle ) ){
                    statement.executeUpdate( "insert into books (book_title) values ('" + bookTitle + "')" );
                    statement.executeUpdate( "insert into book_mapping (id_author, id_book) select id_author, id_book " +
                            "from authors a, books b where a.author = '" + authorName + "' " +
                            "and b.book_title = '" + bookTitle + "'" );
                    resultBookTitle = "";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } );

        findButton.setOnAction( event -> {

            findButton.getScene().getWindow().hide();
            try {
                URL url = new File( "/Users/alex/Documents/SimpleDB/src/main/java/org/example/view/showPage.fxml" ).toURI().toURL();
                loader.setLocation( url );
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle( BeginingController.getName() + ", welcome to Library" );
                stage.setScene( new Scene( root ) );
                stage.show();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );
    }

    private String getAuthorName(){
        return authorNameField.getText();
    }

    private String getBookTitle(){
        return bookTitleField.getText();
    }
}

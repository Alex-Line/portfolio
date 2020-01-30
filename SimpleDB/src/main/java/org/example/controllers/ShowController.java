package org.example.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.database.Driver;

public class ShowController {
    private Driver driver = BeginingController.getDriver();
    private List <String> resultList;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField authorNameField;

    @FXML
    private TextField bookTitleField;

    @FXML
    private Button findButton;

    @FXML
    private Label resultField;

    @FXML
    void initialize() {
        Connection connection = driver.getConnection();


        findButton.setOnAction( event -> {
            try {
                Statement statement = connection.createStatement();
                String authorName = getAuthorName();
                String bookTitle = getBookTitle();

                if(bookTitle.equals( "" ) && authorName.equals( "" )){
                    resultField.setText( "The author and book title were empty. Please enter something relevant." );
                }

                if(!bookTitle.equals( "" ) && authorName.equals( "" )){
                    cleareResultText();
                    resultList = new ArrayList<>(  );
                    Statement statementBookId = connection.createStatement();
                    ResultSet resultSetBook = statementBookId.executeQuery(
                            "select * from books where book_title = '"+ bookTitle + "'" );
                    while (resultSetBook.next()){
                        int idBook = resultSetBook.getInt( 1 );
                        Statement statementAuthorId = connection.createStatement();
                        ResultSet resultSetIdAuthor = statementAuthorId.executeQuery(
                                "select * from book_mapping where id_book = '" + idBook + "'" );
                        while (resultSetIdAuthor.next()){
                            int idAuthor = resultSetIdAuthor.getInt( 1 );
                            Statement statementAuthor = connection.createStatement();
                            ResultSet resultSetAuthor = statementAuthor.executeQuery(
                                    "select * from authors where id_author = '" + idAuthor + "'" );
                            while (resultSetAuthor.next()){
                                resultList.add( resultSetAuthor.getString( 2 ) + " - " + bookTitle );
                            }
                        }
                    }
                    setResultText( resultList );
                }

                if(bookTitle.equals( "" ) && !authorName.equals( "" )){
                    cleareResultText();
                    resultList = new ArrayList<>(  );
                    ResultSet resultSetAuthor = statement.executeQuery(
                            "select * from authors where author = '" + authorName + "'" );
                    while (resultSetAuthor.next()){
                        int idAuthor = resultSetAuthor.getInt( 1 );
                        Statement statementAuthorId = connection.createStatement();
                        ResultSet resultSetIdAuthor = statementAuthorId.executeQuery(
                                "select * from book_mapping where id_author = '" + idAuthor + "'" );
                        while (resultSetIdAuthor.next()) {
                            int idBook = resultSetIdAuthor.getInt( 2 );
                            Statement statementBookId = connection.createStatement();
                            ResultSet resultSetBook = statementBookId.executeQuery(
                                    "select * from books where id_book = '"+ idBook + "'" );
                            while (resultSetBook.next()){
                                resultList.add( authorName + " - " + resultSetBook.getString( 2 ) );
                            }
                        }
                    }
                    setResultText( resultList );
                }

                if(!bookTitle.equals( "" ) && !authorName.equals( "" )){
                    cleareResultText();
                    resultList = new ArrayList<>(  );
                    ResultSet resultSetAuthors = statement.executeQuery(
                            "select * from authors where author = '" + authorName + "'" );
                    while (resultSetAuthors.next()){
                        Statement statementBook = connection.createStatement();
                        ResultSet resultSetBook = statementBook.executeQuery(
                                "select * from book where book_title = '" + bookTitle + "'" );
                        while (resultSetBook.next()){
                            resultList.add( authorName + " - " + resultSetBook.getString( 2 ) );
                        }
                    }
                    setResultText( resultList );
                }

            } catch (SQLException e) {
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

    private void cleareResultText(){
        resultField.setText( "" );
    }

    private void setResultText( List<String> list){
        for (String current : list) {
            resultField.setText( resultField.getText() + current + "\n");
        }
    }
}

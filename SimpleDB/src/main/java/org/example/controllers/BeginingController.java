package org.example.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.database.Driver;

public class BeginingController {
    private static Driver driver = new Driver();
    private static String name;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField loginField;

    @FXML
    private Button signInButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {

        FXMLLoader loader = new FXMLLoader(  );
        signInButton.setOnAction( event -> {
            signInButton.getScene().getWindow().hide();
            try {
                driver.setConnection( getLogin(), getPassword() );
                Connection connection = driver.getConnection();
                Statement statement = connection.createStatement();
                //statement.executeUpdate( "drop table if exists authors" );
                statement.executeUpdate( "create table if not exists authors(id_author int(11) not null auto_increment, author varchar(45) not null, PRIMARY key(id_author))");
                //statement.executeUpdate( "drop table if exists books" );
                statement.executeUpdate( "create table if not exists books(id_book int(11) not null auto_increment, book_title varchar(45) not null, PRIMARY key(id_book))");
                //statement.executeUpdate( "drop table if exists book_mapping" );
                statement.executeUpdate( "create table if not exists book_mapping(id_author int(11) not null, id_book int(11) not null, PRIMARY key(id_book))");
                URL url = new File("/Users/alex/Documents/SimpleDB/src/main/java/org/example/view/addingPage.fxml").toURI().toURL();
                loader.setLocation( url );
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage(  );
                name = getNameField();
                stage.setTitle( name + ", welcome to Library" );
                stage.setScene( new Scene( root ) );
                stage.show();
            } catch (SQLException | MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } );
    }
    public String getNameField(){
        return nameField.getText();
    }

    private String getLogin(){
        return loginField.getText();
    }

    private String getPassword(){
        return passwordField.getText();
    }

    public static Driver getDriver(){
        return driver;
    }

    public static String getName (){
        return name;
    }
}

package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.database.*;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args ) {
        launch( args );
//        List<String> list = new ConsoleReader().getConnectionParametrs();
//        Driver driver = new Driver();
//        driver.setConnection( list );
//        new DBWritter( driver.getConnection() ).writeDataInDatabase();
//        new DBReader( driver.getConnection() ).readDataFromDatabase();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/java/org/example/view/beginingPage.fxml").toURI().toURL();
        Parent root = FXMLLoader.load( url );
        primaryStage.setTitle( "Adding info into Library" );
        primaryStage.setScene( new Scene( root, 600, 400 ) );
        primaryStage.show();
    }
}

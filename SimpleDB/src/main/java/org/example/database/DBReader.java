package org.example.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Deprecated
public class DBReader {
    private Connection connection;

    public DBReader(Connection connection) {
        this.connection = connection;
    }

    public void readDataFromDatabase(){
        try {
            Statement statementAuthors = connection.createStatement();
            ResultSet resultSetAuthors = statementAuthors.executeQuery( "select * from authors" );
            while (resultSetAuthors.next()){
                int idAuthor = resultSetAuthors.getInt( 1 );
                String author = resultSetAuthors.getString( 2 );
                Statement statementBook = connection.createStatement();
                ResultSet resultSetBooks = statementBook.executeQuery( "select book_label from books where id_book = " +
                        "(select id_book from book_mapping where id_authors = " + idAuthor + ")" );

                String book = "";
                while (resultSetBooks.next()){
                    book = resultSetBooks.getString( 1 );
                    System.out.println(idAuthor + ". " + author + " - " + book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

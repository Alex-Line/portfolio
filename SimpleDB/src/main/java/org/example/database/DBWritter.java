package org.example.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Deprecated
public class DBWritter {
    private Connection connection;

    public DBWritter(Connection connection) {
        this.connection = connection;
    }

    public void writeDataInDatabase (){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate( "drop table if exists authors" );
            statement.executeUpdate( "create table if not exists authors(id_authors int(11) not null auto_increment, author varchar(45) not null, PRIMARY key(id_authors))");
            statement.executeUpdate( "insert into authors (author) values ('Tolstoy')" );
            statement.executeUpdate( "insert into authors set author = 'Pushkin'" );
            statement.executeUpdate( "insert into authors (author) values ('Lermontov')" );
            statement.executeUpdate( "insert into authors set author = 'Dostoevskiy'" );

            statement.executeUpdate( "drop table if exists books" );
            statement.executeUpdate( "create table if not exists books(id_book int(11) not null auto_increment, book_title varchar(45) not null, PRIMARY key(id_book))");
            statement.executeUpdate( "insert into books (book_title) values ('Poems')" );
            statement.executeUpdate( "insert into books set book_title = 'Damns'" );
            statement.executeUpdate( "insert into books (book_title) values ('War and Peace')" );
            statement.executeUpdate( "insert into books set book_title = 'Doughter of Kapitan'" );
            statement.executeUpdate( "insert into books set book_title = 'Men of Desember'" );
            statement.executeUpdate( "insert into books set book_title = 'Lukomorie'" );
            statement.executeUpdate( "insert into books set book_title = 'Anna Karenina'" );
            statement.executeUpdate( "insert into books set book_title = 'Mother'" );

            statement.executeUpdate( "drop table if exists book_mapping" );
            statement.executeUpdate( "create table if not exists book_mapping(id_authors int(11) not null, id_book int(11) not null, PRIMARY key(id_book))");
            statement.executeUpdate( "insert into book_mapping set id_authors = '1', id_book = '3'" );
            statement.executeUpdate( "insert into book_mapping set id_authors = '2', id_book = '4'" );
            statement.executeUpdate( "insert into book_mapping set id_authors = '3', id_book = '1'" );
            statement.executeUpdate( "insert into book_mapping set id_authors = '4', id_book = '2'" );
            //statement.executeUpdate( "insert into book_mapping set id_authors = '1', id_book = '5'" );
            //statement.executeUpdate( "insert into book_mapping set id_authors = '2', id_book = '6'" );
            //statement.executeUpdate( "insert into book_mapping set id_authors = '1', id_book = '7'" );
            //statement.executeUpdate( "insert into book_mapping set id_authors = '1', id_book = '8'" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

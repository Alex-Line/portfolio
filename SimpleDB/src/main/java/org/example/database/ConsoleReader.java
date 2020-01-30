package org.example.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ConsoleReader {
    BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
    public List<String> getConnectionParametrs(){
        List<String> list = new ArrayList<>(  );
        list.add( "root" );
        list.add( "root1234" );
//        try{
//            System.out.println("Please enter login for MYSQL database connection: ");
//            list.add( reader.readLine() );
//            System.out.println("Please enter password to your login: ");
//            list.add( reader.readLine() );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return list;
    }
}

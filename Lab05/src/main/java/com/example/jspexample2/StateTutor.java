//program for State and Capital Quiz
package com.example.jspexample2;

import java.sql.*;

public class StateTutor {
    public static String[] State_names = new String[6];
    public static String Random_State;

    public StateTutor() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Loaded");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/javabook","root","root");
        System.out.println("Connected to database");
        ResultSet rs;
        Statement stmt;
        stmt = con.createStatement();
        rs = stmt.executeQuery("select state from statecapital");

        int i = 1;

        while(rs.next())
        {

            State_names[i] = rs.getString("State");
            i++;
        }
    }

    public static void refresh()
    {

        int index1 = (int)(Math.random()* 5) + 1;
        Random_State = State_names[index1];
    }


}

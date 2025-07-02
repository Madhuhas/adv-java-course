package com.example.kotini_sai_madhuhas_700762696_hw3;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class BabyNamesPopulateDB {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/babynames", "root", "root");
        System.out.println("Connected to the database");

        System.out.println("Connected to Database");

        //adding the prepared statement to store the values in the table
        PreparedStatement prestatement = connection.prepareStatement("insert into babyname" + "(byear, bname, gender, bcount)" + "values(?,?,?,?)");

        //declare variables
        int boyNum, girlNum = 0;
        String boyName, girlName = null;
        Scanner data;

        //loop to read files
        for (int year = 2001; year <=2010; year++) {
            //go here to see how a single year of date is formatted
            //http://liveexample.pearsoncmg.com/data/babynamesranking2001.txt
            //there is a separate textfile for each year, 2001 thru 2010
            data = new Scanner(new URL(
                    "http://liveexample.pearsoncmg.com/data/babynamesranking" + year + ".txt").openStream());
            System.out.print(year + " has ");
            int count = 0;

            //loop to read process a single year and insert into db
            while (data.hasNext()){
                count++;
                String s = data.nextLine(); // Skip rank. It is not saved in the table.
                System.out.println(data.nextInt());
                //read boyName
                boyName = data.next();

                //read boyNum
                boyNum = data.nextInt();

                //read girlName
                girlName = data.next();

                //read girlNum
                girlNum = data.nextInt();

                System.out.println(boyName + " "+ boyNum + " " + girlName + " "+ girlNum );




                //insert boy variables into prepared statement and execute
                prestatement.setInt(1,year);
                prestatement.setString(2,boyName);
                prestatement.setString(3,"M");
                prestatement.setInt(4,boyNum);

                prestatement.executeUpdate();

                //insert girl variables into prepared statement and execute
                prestatement.setInt(1,year);
                prestatement.setString(2,girlName);
                prestatement.setString(3,"F");
                prestatement.setInt(4,girlNum);

                prestatement.executeUpdate();


            }
            System.out.println("count= " + count);

        }



    }

}

package com.example.hw4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* This Servlet deals the with the requests coming from BabyNames.html
 * when user clicks the submit button this servlet will get the request and it will read the data
 * using the data this servlet will search for baby names in BabyName database */
@WebServlet(name = "BabyNames", value = "/BabyNames")
public class BabyNamesServlet extends HttpServlet {

    private Connection connection;
    private Statement statement;
    private ResultSet data;

    /*This method is invoked when the first request is given to the servlet since I want to execute this method before handling the request
    * so, I placed the database connection creation logic in this method and this method is executed only once so only one connection object is created*/
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/babynames", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter;
        List<String> namesList = new ArrayList<>();
        String gender, firstLetter, yearInString;

        String queryString;
        int year;

        gender = req.getParameter("gender");
        firstLetter = req.getParameter("firstLetter");
        yearInString = req.getParameter("year");

        if (yearInString == null)
            yearInString = "2001";
        if (firstLetter == null)
            firstLetter = "A";

        year = Integer.parseInt(yearInString);

        if (gender == "M") {
            queryString = "SELECT BNAME FROM BABYNAME WHERE GENDER='" + gender + "' AND BNAME LIKE '" + firstLetter + "%' AND BYEAR=" + year;
        } else {
            queryString = "SELECT BNAME FROM BABYNAME WHERE GENDER='" + gender + "' AND BNAME LIKE '" + firstLetter + "%' AND BYEAR=" + year;
        }

        try {
            statement = connection.createStatement();
            data = statement.executeQuery(queryString);
            while (data.next()) {
                namesList.add(data.getString("BNAME"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        req.setAttribute("names", namesList);
        req.getRequestDispatcher("results.jsp").forward(req, resp);

    }


    @Override
    public void destroy() {
        try {
            connection.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
package com.example.kotini_sai_madhuhas_700762696_hw3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class BabyNames extends Application {

    Statement stmt;
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        initializeDB();

        BorderPane bp = new BorderPane();

        RadioButton boy = new RadioButton("Boy");
        RadioButton girl = new RadioButton("Girl");
        RadioButton either = new RadioButton("Both");
        ToggleGroup genderGroup = new ToggleGroup();
        boy.setToggleGroup(genderGroup);
        girl.setToggleGroup(genderGroup);
        either.setToggleGroup(genderGroup);

        VBox gender = new VBox(3);
        gender.setPadding(new Insets(5,5,5,5));
        // gender.setStyle("=fx-border-width: 2px; -fx-border-color: green");
        gender.getChildren().addAll(boy, girl, either);
        bp.setTop(gender);

        TextArea taResults = new TextArea();
        ScrollPane sp = new ScrollPane(taResults);
        Label results = new Label("Results");
        BorderPane resultPane = new BorderPane();
        resultPane.setTop(results);
        resultPane.setBottom(sp);
        bp.setBottom(resultPane);
        BorderPane masterPane = new BorderPane();
        masterPane.setCenter(bp);

        ComboBox<String> letter = new ComboBox <>();
        letter.setPrefWidth(200);

        Button search = new Button("Search");
        BorderPane fl = new BorderPane();
        fl.setCenter(letter);
        fl.setTop(new Label("Starts with..."));
        fl.setBottom(search);


        String[] alphabet={"A","B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q",
                "R","S","T","U","V","W","X","Y","Z"};
        ObservableList<String> items = FXCollections.observableArrayList(alphabet);
        letter.getItems().addAll(items);

        BorderPane yearList = new BorderPane();

        ComboBox<String> yearDropDown = new ComboBox <>();
        yearDropDown.setPrefWidth(200);
        String[] yr = {"2001", "2002", "2003","2004", "2005", "2006", "2007", "2008", "2009", "2010"};
        ObservableList<String> yritems = FXCollections.observableArrayList(yr);
        yearDropDown.getItems().addAll(yr);
        yearList.setCenter(yearDropDown);
        yearList.setTop(new Label("Year or interest"));
        yearList.setBottom(search);

        VBox vb = new VBox();
        vb.getChildren().addAll(fl, yearList);
        bp.setCenter(vb);


        Scene scene = new Scene(masterPane, 200, 400);
        primaryStage.setTitle("Baby Name Widget");
        primaryStage.setScene(scene);
        primaryStage.show();

        //event handling
        EventHandler<ActionEvent> eventHandler = e->{
            taResults.clear();
            char c = letter.getValue().charAt(0);  //letter selected in drop down box.
            int y = Integer.parseInt(yearDropDown.getValue());
            String queryString = null;

            if (boy.isSelected()) {

                System.out.println("Boy selected" + c);


                //query to find the names of boys with the selected starting letter and year of birth
                queryString = "select bname from babyname where bname like '"+c+"%' and byear="+y+"    and gender='M'";

            }
            else if (girl.isSelected()){

                System.out.println("Girl selected " + c );

                //query to find the names of girls with the selected starting letter and year of birth
                queryString = "select bname from babyname where bname like '"+c+"%' and byear="+y+"    and gender='F'";

            }
            else{

                //query to find the names of both boys and girls with the selected starting letter and year of birth
                queryString = "select bname from babyname where bname like '"+c+"%' and byear="+y;
                System.out.println("Either");
            }

            try {  //#4
                ResultSet rs = stmt.executeQuery(queryString);
                taResults.clear();
                int rcount = 0;
                while (rs.next()){
                    rcount++;
                    String name = rs.getString(1);
                    taResults.appendText(name + "\n");
                }

                System.out.println("row count " + rcount); //this is just to check that displayed number of rows returned
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        };
        search.setOnAction(eventHandler);  //associate event handling on the search button.


    }//end start

    private void makeQuery(){
    }

    public void initializeDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/babynames", "root", "root");
        stmt = connection.createStatement();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

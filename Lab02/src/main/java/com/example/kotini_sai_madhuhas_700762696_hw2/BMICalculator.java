package com.example.kotini_sai_madhuhas_700762696_hw2;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BMICalculator extends Application {

    private TextField name = new TextField();
    private TextField age = new TextField();
    private TextField height = new TextField();
    private TextField weight = new TextField();
    private TextField bmi = new TextField();
    private TextField status = new TextField();
    private Button calculateBtn = new Button("Calculate");

    @Override // Override the start method in Application class
    public void start(Stage primaryStage) throws Exception {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(name, 1, 0);
        gridPane.add(new Label("Age:"), 0, 1);
        gridPane.add(age, 1, 1);
        gridPane.add(new Label("Height:"), 0, 2);
        gridPane.add(height, 1, 2);
        gridPane.add(new Label("Weight:"), 0, 3);
        gridPane.add(weight, 1, 3);
        gridPane.add(new Label("BMI:"), 0, 4);
        gridPane.add(bmi, 1, 4);
        gridPane.add(new Label("Status:"), 0, 5);
        gridPane.add(status, 1, 5);
        gridPane.add(calculateBtn, 2, 5);

        // Set Properties for UI
        gridPane.setAlignment(Pos.CENTER);
        name.setAlignment(Pos.BOTTOM_RIGHT);
        age.setAlignment(Pos.BOTTOM_RIGHT);
        height.setAlignment(Pos.BOTTOM_RIGHT);
        weight.setAlignment(Pos.BOTTOM_RIGHT);
        bmi.setAlignment(Pos.BOTTOM_RIGHT);
        bmi.setEditable(false);
        status.setAlignment(Pos.BOTTOM_RIGHT);
        status.setEditable(false);

        gridPane.setHalignment(calculateBtn, HPos.RIGHT);

        // Process Events
        calculateBtn.setOnAction(e -> calculateBMI());

        // Create a scene and pl
        // ace it in the Stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("BMI Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateBMI() {
        String name = this.name.getText();
        int age = Integer.parseInt(this.age.getText());
        double height = Double.parseDouble(this.height.getText());
        double weight = Double.parseDouble(this.weight.getText());

        BMI bmi = new BMI(name, age, weight, height);
        this.bmi.setText(bmi.getBMI() + "");
        status.setText(bmi.getStatus());
    }

}



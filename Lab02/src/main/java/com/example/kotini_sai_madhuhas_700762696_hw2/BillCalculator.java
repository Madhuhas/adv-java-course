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

public class BillCalculator extends Application {
    private TextField itemPrice = new TextField();
    private TextField quantity = new TextField();
    private TextField total = new TextField();
    private Button calculateBtn = new Button("Calculate");


    @Override // Override the start method in Application class
    public void start(Stage primaryStage) throws Exception {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Item Price:"), 0, 0);
        gridPane.add(itemPrice, 1, 0);
        gridPane.add(new Label("Quantity Purchased:"), 0, 1);
        gridPane.add(quantity, 1, 1);
        gridPane.add(new Label("Amount Due:"), 0, 2);
        gridPane.add(total, 1, 2);
        gridPane.add(calculateBtn, 1, 3);

        // Set Properties for UI
        gridPane.setAlignment(Pos.CENTER);
        itemPrice.setAlignment(Pos.BOTTOM_RIGHT);
        quantity.setAlignment(Pos.BOTTOM_RIGHT);
        total.setAlignment(Pos.BOTTOM_RIGHT);
        total.setEditable(false);
        gridPane.setHalignment(calculateBtn, HPos.RIGHT);

        // Process Events
        calculateBtn.setOnAction(e -> calculateTotalBill());

        // Create a scene and place it in the Stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Purchase Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateTotalBill() {
        double price = Double.parseDouble(itemPrice.getText());
        double qty = Double.parseDouble(quantity.getText());
        double total = Math.round(price * qty * 100) / 100.0;
        this.total.setText(String.format("$%.2f", total));

    }
}

/*
Name: Theo Shaffer
Date: 10.2.2024
Professor: Zareen Farooqi
Displays a menu system for an unnamed phone company. Provides the option for 3 phone plans containing 8, 16, and 20 gigabytes.
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PhonePlanSelections extends Application {
    // Phone types and prices defined in arrays
    private static final String[] PHONE_TYPES = {"Model 100", "Model 110", "Model 200"};
    private static final double[] PHONE_PRICES = {299.95, 399.95, 499.95};
    
    // Plan levels defined in arrays
    private static final String[] PLANS = {"8 gigabytes per month", "16 gigabytes per month", "20 gigabytes per month"};
    private static final double[] PLAN_PRICES = {45.00, 65.00, 99.00};
    
    // Add-ons defined in arrays
    private static final String[] ADD_ONS = {"Phone Replacement Insurance", "WiFi Hotspot Capability"};
    private static final double[] ADD_ON_PRICES = {5.00, 10.00};
    
    private ComboBox<String> phoneComboBox;
    private ComboBox<String> planComboBox;
    private CheckBox[] addOnCheckBoxes;
    private TextArea summaryTextArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override 
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Phone Plan Selections"); 

        // Phone Selection
        phoneComboBox = new ComboBox<>();
        phoneComboBox.getItems().addAll(PHONE_TYPES); 
        
        // Plan Selection
        planComboBox = new ComboBox<>();
        planComboBox.getItems().addAll(PLANS);
        
        // Add-On Selection using CheckBoxes
        VBox addOnBox = new VBox(10);
        addOnCheckBoxes = new CheckBox[ADD_ONS.length];
        for (int i = 0; i < ADD_ONS.length; i++) {
            addOnCheckBoxes[i] = new CheckBox(ADD_ONS[i]);
            addOnBox.getChildren().add(addOnCheckBoxes[i]);
        }

        // Summary text area
        summaryTextArea = new TextArea();
        summaryTextArea.setEditable(false); 
        
        // Buttons
        Button calculateButton = new Button("Calculate Total");
        calculateButton.setOnAction(e -> calculateTotal()); 
        
        Button confirmButton = new Button("Confirm Selection");
        confirmButton.setOnAction(e -> confirmSelection()); 
        
        // Layout 
        VBox layout = new VBox(10); 
        layout.setPadding(new Insets(20)); 
        layout.getChildren().addAll(
            new Label("Select a Phone:"), phoneComboBox,
            new Label("Select a Plan:"), planComboBox,
            new Label("Select Add-Ons:"), addOnBox,
            calculateButton,
            new Label("Summary:"), summaryTextArea,
            confirmButton 
        );

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     
    private void calculateTotal() {
        String selectedPhone = phoneComboBox.getValue();
        double phonePrice = getPrice(selectedPhone, PHONE_TYPES, PHONE_PRICES);
        
        String selectedPlan = planComboBox.getValue();
        double planPrice = getPrice(selectedPlan, PLANS, PLAN_PRICES);
        
        double addOnTotal = calculateAddOnTotal();

        double salesTax = (phonePrice + planPrice + addOnTotal) * 0.06;
        double totalPrice = phonePrice + planPrice + addOnTotal + salesTax;
        
        // Display summary
        StringBuilder summary = new StringBuilder();
        summary.append("Phone: ").append(selectedPhone).append(" - $").append(String.format("%.2f", phonePrice)).append("\n")
                .append("Plan: ").append(selectedPlan).append(" - $").append(String.format("%.2f", planPrice)).append("\n");

        summary.append("Add-ons: ");
        boolean hasAddOns = false;
        for (CheckBox checkBox : addOnCheckBoxes) {
            if (checkBox.isSelected()) {
                if (hasAddOns) {
                    summary.append(", ");
                }
                summary.append(checkBox.getText());
                hasAddOns = true;
            }
        }
        if (hasAddOns) {
            summary.append("\nAdd-ons Total: $").append(String.format("%.2f", addOnTotal)).append("\n");
        } else {
            summary.append("None\n");
        }

        summary.append("Sales Tax: $").append(String.format("%.2f", salesTax)).append("\n")
                .append("Total Price: $").append(String.format("%.2f", totalPrice));

        summaryTextArea.setText(summary.toString());
    }

    private double getPrice(String selectedItem, String[] items, double[] prices) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(selectedItem)) {
                return prices[i];
            }
        }
        return 0.0;
    }

    private double calculateAddOnTotal() {
        double total = 0.0;
        for (int i = 0; i < addOnCheckBoxes.length; i++) {
            if (addOnCheckBoxes[i].isSelected()) {
                total += ADD_ON_PRICES[i];
            }
        }
        return total;
    }

    private void confirmSelection() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been confirmed.");
        alert.showAndWait();
    }
}

package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private GridPane ui;
    private Label healthTextLabel;
    private Label healthValueLabel;
    private Label damageTextLabel;
    private Label damageValueLabel;
    private Label inventoryTextLabel;
    private Label inventoryValueLabel;
    private Label killCountTextLabel;
    private Label killCountValueLabel;

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        damageTextLabel = new Label("Damage: ");
        damageValueLabel = new Label();
        killCountTextLabel = new Label("Total Kill:\n\n" + "-".repeat(20));// could be different line as separator
        killCountValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory:\n\n" + "-".repeat(20));// could be different line as separator
        inventoryValueLabel = new Label();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(damageTextLabel, 0, 1);
        ui.add(damageValueLabel, 1, 1);
        ui.add(killCountTextLabel, 0, 2);
        ui.add(killCountValueLabel, 1, 2);
        ui.add(inventoryTextLabel, 0, 3);
        ui.add(inventoryValueLabel, 0, 4);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void setDamageValue(String text) {
        damageValueLabel.setText(text);
    }

    public void setInventoryValue(String text) {
        inventoryValueLabel.setText(text);
    }

    public void setKillCountValue(String text) {
        killCountValueLabel.setText(text);
    }
}

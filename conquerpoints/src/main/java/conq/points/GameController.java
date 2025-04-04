package conq.points;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameController {

    public static final int SIZE = 10; // Spielfeldgröße
    public static final int CELL_SIZE = 80; // Größe der Felder
    public static boolean player = false; // false -> blau, sonst rot
    public static GameCell[][] cells = new GameCell[CELL_SIZE][CELL_SIZE];

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.LIGHTGRAY);
                cell.setStroke(Color.BLACK);

                Label label = new Label("");
                label.setStyle("-fx-font-size: 24px; -fx-text-fill: black;");
                label.setVisible(false); // initial unsichtbar

                StackPane cellContainer = new StackPane(cell, label);
                int finalRow = row;
                int finalCol = col;

                // Klick-Event hinzufügen
                cell.setOnMouseClicked(event -> {
                    clickEvent(cells[finalCol][finalRow]); // Zugriff auf GameCell
                });
                
                gridPane.add(cell, col, row);
                cells[col][row] = new GameCell(cell, label, finalCol, finalRow);
            }
        }
    }

    public void clickEvent(GameCell cell){
        if(!cell.setPoint()) {
            return;
        }
        player = !player;
    }
}
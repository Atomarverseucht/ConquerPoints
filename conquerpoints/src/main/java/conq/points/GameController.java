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
    public static GameCell[][] cells = new GameCell[SIZE][SIZE];

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

                label.setVisible(false);
                // Klick-Event hinzufügen
                cell.setOnMouseClicked(event -> {
                    clickEvent(cells[finalCol][finalRow]); // Zugriff auf GameCell
                });
                
                gridPane.add(cellContainer, col, row);
                cells[col][row] = new GameCell(cell, label, finalCol, finalRow);
            }
        }
    }

    public void clickEvent(GameCell cell){
        if(!cell.setPoint()) {
            return;
        }
        playerChange();
    }

    public static Color getPlayerColor(){
        return (player ? Color.RED : Color.BLUE);
    }
    public static void playerChange(){
        player = !player;
        final Color c = getPlayerColor();
        for (GameCell[] gameCells : cells) {
            for (GameCell gameCell : gameCells) {
                gameCell.getRect().setStroke(c);
            }
        }
    }
}
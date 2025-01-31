package conq.points;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameController {

    private static final int SIZE = 10; // Spielfeldgröße
    private static final int CELL_SIZE = 80; // Größe der Felder

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.LIGHTGRAY);
                cell.setStroke(Color.BLACK);

                // Klick-Event für Farbwechsel
                cell.setOnMouseClicked(event -> {
                    cell.setFill(cell.getFill().equals(Color.LIGHTGRAY) ? Color.BLUE : Color.LIGHTGRAY);
                });

                gridPane.add(cell, col, row);
            }
        }
    }
}
package conq.points;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameCell {
    private final Rectangle rect;
    private final Label label;
    private final int col;
    private final int row;
    private int value = 0;

    public GameCell(Rectangle rect, Label label, int col, int row) {
        this.rect = rect;
        this.label = label;
        this.col = col;
        this.row = row;
    }

    public Rectangle getRect(){
        return rect;
    }

    public void setValue(int newValue) {
        this.value = newValue;
        if (newValue > 0 && newValue <= 3) {
            label.setText(String.valueOf(newValue));
            label.setVisible(true);
        } else {
            label.setText("");
            label.setVisible(false);
        }
    }

    public int getValue() {
        return value;
    }

    public int col(){
        return col;
    }

    public int getRow(){
        return row;
    }

    public void addV(){
        value += 1;
        label.setVisible(true);
    }

    public boolean setPoint(){
        Color col = GameController.player ? Color.RED : Color.BLUE;
        if(rect.getFill().equals(Color.LIGHTGRAY)){
            rect.setFill(col);
            addV();
            return true;
        } else if(rect.getFill().equals(col)){
            addV();
            if(getValue() == 4){
                value = 0;

            }
            return true;
        }
        
        return false;
    }

    public void conqCell(){
        Color col = GameController.player ? Color.RED : Color.BLUE;
        rect.setFill(col);
        
    }

    public GameCell[] neighbourC(){
        GameController gc = new GameController();
        final GameCell[][] in = GameController.cells;
        GameCell[] out = new GameCell[4];
        if(row > 0) out[0] = in[col][row-1];
        if(col > 0) out[1] = in[col-1][row];
        if(row < gc.CELL_SIZE) out[2] = in[col][row+1];
        if(col < gc.CELL_SIZE) out[3] = in[col+1][row];
        return out;
    }
}

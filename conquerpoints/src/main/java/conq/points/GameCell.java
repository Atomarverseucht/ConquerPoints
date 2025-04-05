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

        this.label.setTextFill(Color.WHITE);
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
        if(value>=4){
            value = 0;
            label.setVisible(false);
            rect.setFill(Color.LIGHTGRAY);
            GameCell[] nbc = neighbourC();
            for (GameCell neighbour : nbc) {
                if(neighbour != null){
                    neighbour.conq();
                }
            }
        } else{
            label.setVisible(true);
            label.setText(Integer.toString(value));
        }
        
    }

    public boolean setPoint(){
        final Color col = GameController.getPlayerColor();
        if(rect.getFill().equals(Color.LIGHTGRAY) && GameController.rounds <= 3){
            rect.setFill(col);
            addV();
            return true;
        } else if(rect.getFill().equals(col)){
            addV();
            return true;
        }
        
        return false;
    }

    public void conq(){
        final Color col = GameController.player ? Color.RED : Color.BLUE;
        rect.setFill(col);
        addV();
    }

    public GameCell[] neighbourC(){
        final int SIZE = GameController.SIZE;
        final GameCell[][] in = GameController.cells;
        GameCell[] out = new GameCell[4];
        if(row > 0) out[0] = in[col][row-1];
        if(col > 0) out[1] = in[col-1][row];
        if(row < SIZE) out[2] = in[col][row+1];
        if(col < SIZE) out[3] = in[col+1][row];
        return out;
    }
}

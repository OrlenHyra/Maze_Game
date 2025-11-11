

import java.awt.event.*;
import javax.swing.*;
public class Player extends JPanel implements ActionListener{

    private int thesare;
    private int xPos;
    private int yPos;
    private final Board board;
    private final int level;

    public Player(Board board, int level){
        this.board = board;
        this.level = level;
        this.thesare = 0;
        xPos = 1;
        yPos = 1;
    }

    public Player(Board board, int xPos, int yPos, int thesare, int level){
        this.board = board;
        this.level = level;
        this.xPos = xPos;
        this.yPos = yPos;
        this.thesare = thesare;
    }

    public void actionPerformed(ActionEvent e){
        repaint();
    }

    public void moveLeft(Board board) {
        if ((board.get(xPos - 1, yPos) != '#') && (board.get(xPos - 1, yPos) != '=')) {
             board.set(xPos, yPos, 'O');
            if ((board.get(xPos - 1, yPos) == '8') && (thesare == level)) {
                Win();
            } else if ((board.get(xPos - 1, yPos) == '8') && (thesare != level)) {
                Lose();
            } else if (board.get(xPos - 1, yPos) == '+') {
                thesare++;
                board.set(xPos - 1, yPos, 'X');
            } else {
                board.set(xPos - 1, yPos, 'X');
            }
            xPos--;
        }
    }
    public void moveRight(Board board) {
        if ((board.get(xPos + 1, yPos) != '#') && (board.get(xPos + 1, yPos) != '=')) {
            board.set(xPos, yPos, 'O');
            if ((board.get(xPos + 1, yPos) == '8') && (thesare == level)) {
                Win();
            } else if ((board.get(xPos + 1, yPos) == '8') && (thesare != level)) {
                Lose();
            } else if (board.get(xPos + 1, yPos) == '+') {
                thesare++;
                board.set(xPos + 1, yPos, 'X');
            } else {
                board.set(xPos + 1, yPos, 'X');
            }
            xPos++;
        }
    }
    public void moveUp(Board board) {
        if ((board.get(xPos, yPos - 1) != '#') && (board.get(xPos, yPos - 1) != '=')) {
            board.set(xPos, yPos, 'O');
            if ((board.get(xPos, yPos - 1) == '8') && (thesare == level)) {
                Win();
            } else if ((board.get(xPos, yPos - 1) == '8') && (thesare != level)) {
                Lose();
            } else if (board.get(xPos, yPos - 1) == '+') {
                thesare++;
                board.set(xPos, yPos - 1, 'X'); // Set the cell to 'X' after collecting the coin
            } else {
                board.set(xPos, yPos - 1, 'X');
            }
            yPos--; // Move the player's position after processing
        }
    }
    // Move down method
    public void moveDown(Board board) {
        if ((board.get(xPos, yPos + 1) != '#') && (board.get(xPos, yPos + 1) != '=')) {
            board.set(xPos, yPos, 'O'); //pozicioni kur vendoset lojtari behet ngjyre blu si vet lojtari
            if ((board.get(xPos, yPos + 1) == '8') && (thesare == level)) {
                Win();
            } else if ((board.get(xPos, yPos + 1) == '8') && (thesare != level)) {
                Lose();
            } else if (board.get(xPos, yPos + 1) == '+') {
                thesare++;
                board.set(xPos, yPos + 1, 'X'); //kur e merr monedhen nga kutia ku ishte lojtaria behet prap me vlere x behet e bardhe
            } else {
                board.set(xPos, yPos + 1, 'X'); // edhe kur largohet lojtari behet prap e bardh
            }
            yPos++;
        }
    }
    public void Win(){
        new MazeFrame(level, thesare, 'W');
    }

    public void Lose(){
        new MazeFrame(level, thesare, 'L');
    }

    public int getLevel() {
        return level;
    }

    public int getThesare() {
        return thesare;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
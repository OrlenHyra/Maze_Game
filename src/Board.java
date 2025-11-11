
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Board extends JPanel {

    private char[][] board;
    private int size;
    private int unVisited;
    private int scale;
    LinkedList<Position> positionList = new LinkedList<Position>();
    public Board(int x, int y, int obj) {
        unVisited = (x * x);
        x *= 2;
        y *= 2;
        x++;
        y++;
        scale = y;

        board = new char[x][y];
        size = x;
        generateBoard();
        addObject(obj);
        printBoard();
    }

    public Board(char[][] board){
        this.board = board;
        scale = board.length;
        size = scale;
    }

    public void generateBoard() {
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                board[i][k] = 'u';
            }
        }

        for (int i = 0; i < size; i += 2) {
            for (int k = 0; k < size; k++) {
                board[i][k] = '=';
                board[k][i] = '=';
            }
        }
        for (int i = 0; i < size; i++) {
            board[i][0] = '#';
            board[0][i] = '#';
            board[size - 1][i] = '#';
            board[i][size - 1] = '#';
        }
        printBoard();
        generate(1, 1);
    }

    public void paint(Graphics g) {
        super.paint(g);
        int n = 500 / scale;

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if ((board[i][k] == '#')) { // muri anesor
                    g.setColor(Color.black);
                    g.fillRect(i * n, k * n, n, n);
                } else if (board[i][k] == '=') { //muret e brendshme
                    g.setColor(Color.gray);
                    g.fillRect(i * n, k * n, n, n);
                } else if (board[i][k] == '8') { // dalja
                    g.setColor(Color.red);
                    g.fillRect(i * n, k * n, n, n);
                } else if (board[i][k] == 'X') { //lojtari
                    g.setColor(Color.blue);
                    g.fillRect(i * n, k * n, n, n);
                } else if (board[i][k] == '+') { //thesaret
                    g.setColor(Color.yellow);
                    g.fillOval(i * n, k * n, n, n);
                }
            }
        }
    }
    public char get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, char value) {
        board[x][y] = value;
        repaint();
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                System.out.print(board[k][i]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public void addObject(int amount) {
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            int xPos = 0, yPos = 0;

            do {
                xPos = random.nextInt(size);
                yPos = random.nextInt(size);
            } while (get(xPos, yPos) != 'v');

            set(xPos, yPos, '+');
        }
    }

    public class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public char[] updateDirection(Position cC) { //ky konstruktor tregon si ndryshojn kordinatat kur leviz lindje p, v , j
        char north = 0, south = 0, east = 0, west = 0;

        if (get(cC.getX(), cC.getY() + 1) != '#')
            east = get(cC.getX(), cC.getY() + 2);
        if (get(cC.getX(), cC.getY() - 1) != '#')
            west = get(cC.getX(), cC.getY() - 2);
        if (get(cC.getX() - 1, cC.getY()) != '#')
            north = get(cC.getX() - 2, cC.getY());
        if (get(cC.getX() + 1, cC.getY()) != '#')
            south = get(cC.getX() + 2, cC.getY());
        char direction[] = {west, east, south, north};
        return direction;
    }


    Position cC = new Position(5,5);

    public void generate(int posX, int posY){
        cC = new Position(posX,posY);
        set(cC.getX(),cC.getY(), 'v');
        unVisited-=1;

        char north=0,south=0,east=0,west=0;
        char direction[] = {west,east,south,north};

        direction = updateDirection(cC);

        while(unVisited != 0){
            int free = 0;
            if((direction[0] == 'u') || (direction[1] == 'u') || (direction[2] == 'u') || (direction[3] == 'u'))
                free = 1;

            Random generator = new Random();
            int random = generator.nextInt(4);
            set(cC.getX(),cC.getY(), 'v');

            if((random == 0) && (direction[0] == 'u')){ //West
                if (get(cC.getX(),cC.getY()-1) != '#'){
                    set(cC.getX(), cC.getY()-1, 'v');
                    cC = new Position(cC.getX(), cC.getY()-2);
                    positionList.push(cC);

                    direction = updateDirection(cC);
                    unVisited--;

                }
            }
            else if((random == 1) && (direction[1] == 'u')){ //East
                if (get(cC.getX(),cC.getY()+1) != '#'){
                    set(cC.getX(), cC.getY()+1, 'v');
                    cC = new Position(cC.getX(), cC.getY()+2);
                    positionList.push(cC);

                    direction = updateDirection(cC);
                    unVisited--;
                }
            }

            else if((random == 2) && (direction[2] == 'u')){ //South
                if (get(cC.getX()+1,cC.getY()) != '#'){
                    set(cC.getX()+1, cC.getY(), 'v');
                    cC = new Position(cC.getX()+2, cC.getY());
                    positionList.push(cC);

                    direction = updateDirection(cC);
                    unVisited--;

                }
            }
            else if((random == 3) && (direction[3] == 'u')){ //North
                if (get(cC.getX()-1,cC.getY()) != '#'){
                    set(cC.getX()-1, cC.getY(), 'v');
                    cC = new Position(cC.getX()-2, cC.getY());
                    positionList.push(cC);

                    direction = updateDirection(cC);
                    unVisited--;

                }
            } else {
                if(free == 0 && positionList.size() != 0){ // free=0 kemi gjitha drejtimet v
                    cC = positionList.get(positionList.size()-1);
                    positionList.remove(positionList.size()-1);//position list -1 fshin nje vend te lire'u' nga lista
                    direction = updateDirection(cC);


                }
            }
        }
        set(cC.getX(),cC.getY(),'8');
        set(1,1,'X');
    }

    public char[][] getBoard() {
        return board;
    }
}
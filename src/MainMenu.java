
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.*;
public class MainMenu extends JFrame {

    JFrame frame = new JFrame("Maze");
    public MainMenu(){

        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);

        Button play = new Button("Play");
        play.setFont(new Font("Verdana", Font.BOLD, 32));

        Button loadGame = new Button("Load saved game");
        loadGame.setFont(new Font("Verdana", Font.BOLD, 32));
        frame.add(play);
        frame.add(loadGame);

        frame.setLayout(boxLayout);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        play.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.dispose();
                new Difficulty();
                System.out.println("Starting new game");
            }
        });

        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                Object[] loadedObjects = new Object[2];

                try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("labirinti.dat"))) {
                    loadedObjects[0] = (char[][]) objectInputStream.readObject();
                    loadedObjects[1] = (int[])objectInputStream.readObject();
                    System.out.println("Game loaded successfully.");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                for (int i = 0; i < ((char[][])loadedObjects[0]).length; i++) {
                    for (int k = 0; k < ((char[][])loadedObjects[0])[0].length; k++) {
                        System.out.print(((char[][])loadedObjects[0])[k][i]);
                        System.out.print(" ");
                    }
                    System.out.println("");
                }

                System.out.println("X = " + ((int[])loadedObjects[1])[0]);
                System.out.println("y = " + ((int[])loadedObjects[1])[1]);
                System.out.println("Thesare = " + ((int[])loadedObjects[1])[2]);
                System.out.println("Niveli = " + ((int[])loadedObjects[1])[3]);
                new MazeFrame((char[][])loadedObjects[0], (int[])loadedObjects[1]);
            }
        });
    }
}
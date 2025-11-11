import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class MazeFrame {

    JFrame frame3 = new JFrame("Maze");
    JPanel paneliLabirintit = new JPanel(new BorderLayout());
    JPanel paneliButonave = new JPanel(new BorderLayout());

    public MazeFrame(char[][] board, int[] playerStats){


        Board test = new Board(board);
        Player player = new Player(test, playerStats[0], playerStats[1], playerStats[2], playerStats[3]);

        paneliLabirintit.add(test, BorderLayout.CENTER);
        paneliLabirintit.setFocusable(true);
        JButton saveGame = new JButton("Save Game");
        saveGame.setFocusable(false);
        frame3.add(paneliLabirintit, BorderLayout.CENTER);
        frame3.add(paneliButonave, BorderLayout.SOUTH);
        paneliButonave.add(saveGame, BorderLayout.SOUTH);
        saveGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                SaveGame.SaveGame(test.getBoard(),player);
            }
        });

        JButton menu = new JButton("New Game");
        menu.setFocusable(false);
        paneliButonave.add(menu, BorderLayout.CENTER);
        menu.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame3.dispose();
                new MainMenu();
            }
        });

        paneliLabirintit.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if(e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT) player.moveLeft(test);
                if(e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT) player.moveRight(test);
                if(e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP) player.moveUp(test);
                if(e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN) player.moveDown(test);
            }
        });

        frame3.setSize(510, 600);
        frame3.setLocationRelativeTo(null);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setResizable(false);
        frame3.setBackground(Color.green);
        frame3.setVisible(true);
    }
    public MazeFrame(int level){


        Board test = new Board(level,level,level);
        Player player = new Player(test, level);

        paneliLabirintit.add(test, BorderLayout.CENTER);
        paneliLabirintit.setFocusable(true);

        frame3.add(paneliLabirintit, BorderLayout.CENTER);
        frame3.add(paneliButonave, BorderLayout.SOUTH);



       JButton saveGame = new JButton("Save Game");
        saveGame.setFocusable(false);
        paneliButonave.add(saveGame, BorderLayout.SOUTH);
        saveGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                SaveGame.SaveGame(test.getBoard(),player);
            }
        });

        JButton menu = new JButton("New Game");

        paneliButonave.add(menu, BorderLayout.CENTER);
        menu.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame3.dispose();
                new MainMenu();
            }
        });

        paneliLabirintit.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if(e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT) player.moveLeft(test);
                if(e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT) player.moveRight(test);
                if(e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP) player.moveUp(test);
                if(e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN) player.moveDown(test);
            }
        });

        frame3.setSize(510, 600);
        frame3.setLocationRelativeTo(null);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setResizable(false);
        frame3.setBackground(Color.green);
        frame3.setVisible(true);
    }

    public MazeFrame(int level, int thesare, char gjendja) {
        frame3.dispose();

        JFrame frame2 = new JFrame();
        JLabel textLabel;
        if (gjendja == 'L') {
            textLabel = new JLabel("<html>Ju kaluat daljen me " + thesare + " thesare nga " + level + " thesare!<br>Ju humbet!<html>", JLabel.CENTER);
        } else {
            textLabel = new JLabel("<html>Ju kaluat daljen me " + thesare + " thesare nga " + level + " thesare!<br>Ju fituat!<html>", JLabel.CENTER);
        }

        textLabel.setFont(new Font("Verdana", Font.BOLD, 32));

        // Adding padding using an EmptyBorder
        textLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        frame2.setBackground(Color.green);
        frame2.add(textLabel, BorderLayout.CENTER);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(500, 500);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
    }
}
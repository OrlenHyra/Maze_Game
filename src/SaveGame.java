import java.io.*;

public class SaveGame {
    public static void SaveGame(char[][] board, Player player){
        int[] playerStats = {player.getxPos(), player.getyPos(), player.getThesare(), player.getLevel()};
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("labirinti.dat"))) {
            objectOutputStream.writeObject(board);
            objectOutputStream.writeObject(playerStats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

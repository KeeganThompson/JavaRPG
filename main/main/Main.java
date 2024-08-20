package main;

import javax.swing.JFrame;

public class Main {

    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame(); //make frame of game
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //game exit
        window.setResizable(false);
        window.setTitle("Game");

        //make new game panel object
        GamePanel gamePanel = new GamePanel();
        //add gamepanel to window
        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn == true) {
            window.setUndecorated(true);
        }

        //window fits preffered size
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        //starts game loop
        gamePanel.startGameThread();
    }
}
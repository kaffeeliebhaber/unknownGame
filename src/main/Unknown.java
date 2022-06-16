package main;

import core.game.GameUnknown;

import javax.swing.*;

public class Unknown {

    public static void main(String... args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("unknown ?");

        GamePanel gamePanel = new GamePanel(new GameUnknown());
        frame.add(gamePanel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.start();
    }

}

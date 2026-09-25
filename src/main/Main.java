package main;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {

        String f0034 = "My 2d Game"; //Title


        //window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(f0034);


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

}

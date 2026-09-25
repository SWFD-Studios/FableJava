package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    //Screen settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 2;

    final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 40;
    final int maxScreenRow = 22;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight =  tileSize * maxScreenRow;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }
}

package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 2;

    final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 40;
    final int maxScreenRow = 22;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight =  tileSize * maxScreenRow;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);

    }


    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    // The Following is the GameLoop
    @Override
    public void run()
    {

        //Logs
        System.out.println("The game loop has been triggered.");


        while (gameThread != null)
        {

            // System.out.println("The game loop is running.");

            // 1 UPDATE: update information such as character position
            update();


            // 2 Draw: draw the screen with the updated information
            repaint();
        }
        //Game LOOP


    }

    public void update()
    {

    }

    public void paintComponent(Graphics g)
    {
        //this is a paintbrush
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.black);

//        int x = 0;
//        int y = 0;
//        boolean start = true;
//        int size = 24;
//        int o = 0;
//        while (start){
//            if(x>screenWidth)
//            {x=0; y++;}
//            if(y>screenHeight)
//            {start=false;x=0;y=0;}
//            if(x%2==0&&y%2==0 || x%2==(1-o)&&y%2==1){
//                    g2.fillRect(x*size,y*size, size, size);
//            }
//            x++;
//
//
//        }
//        if(o==1){o=0;}else{o=1;}
        g2.dispose();
    }


}

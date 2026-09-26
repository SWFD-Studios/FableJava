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

    //Frames per Second
    int FPS = 60;


    KeyHandler keyH = new KeyHandler();

    Thread gameThread;

    //Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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
            /*long currentTime = System.nanoTime();
            //long currentTime2 = System.currentTimeMillis();
            System.out.println("current Time:"+currentTime);*/
            // System.out.println("The game loop is running.");
            //first method for fps= sleep
            double drawInterval = (double) 1000000000 /FPS; //For 60FPS= 0.166666 seconds per frame
            double nextDrawTime = System.nanoTime() + drawInterval;


            // 1 UPDATE: update information such as character position
            update();

            // 2 Draw: draw the screen with the updated information
            repaint();

            try {
                double remainingTime =  nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                Thread.sleep((long) remainingTime);
                if(remainingTime<0){
                    remainingTime = 0;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        //Game LOOP


    }

    public void update()
    {
        if(keyH.upPressed == true)
        {
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed == true)
        {
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed == true)
        {
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed == true)
        {
            playerX += playerSpeed;
        }
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

        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }


}

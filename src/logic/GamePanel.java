package logic;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entities.Entity;
import entities.Player;
import gameFolder.Sound;
import gameFolder.UI;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20; //original 16
    public final int maxScreenRow = 15; //original 12
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //fps
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound sEffect = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    public Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[10];  //increase number to increase max number of object on screen
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        //playMusic(0);
        gameState = titleState;

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>=1) {
                // update information eg positions
                update();
                // draw the screen with updated information
                repaint();
                delta--;
                drawCount++;
            }

            if(timer>=1000000000){
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }

    }

    public void update() {
        if(gameState == playState){
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    if(monster[i].alive && !monster[i].dying){
                        monster[i].update();
                    }if(!monster[i].alive){
                        monster[i] = null;
                    }

                }
            }
        }
        if(gameState == playState){
            //nothing
        }
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);

        } else {

            //TILE
            tileM.draw(g2);

            //add entity to list
            entityList.add(player);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null){
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null){
                    entityList.add(monster[i]);
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });

            //Draw entities
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            entityList.clear();

            ui.draw(g2);

        }
        if(keyH.showDebugText){
            g2.setFont( new Font("Arial",Font.PLAIN,20));
            g2.setColor(Color.WHITE);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX " + player.worldX,x,y);
            y += lineHeight;
            g2.drawString("WorldY " + player.worldY,x,y);
            y += lineHeight;
            g2.drawString("Col " + (player.worldX + player.solidArea.x)/tileSize ,x,y);
            y += lineHeight;
            g2.drawString("Row " + (player.worldY + player.solidArea.y)/tileSize,x,y);

        }
        g2.dispose();
    }

        public void playMusic ( int i){
            music.setFile(i);
            music.play();
            music.loop();
        }
        public void stopMusic () {
            music.stop();
        }
        public void playSE ( int i){
            sEffect.setFile(i);
            sEffect.play();
        }

}

package gameFolder;

import logic.GamePanel;
import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font cartoon, alagard, arial_28;
    BufferedImage heart_full, heart_half,heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try{
            InputStream is = getClass().getResourceAsStream("/resources/fonts/alagard.ttf");
            alagard = Font.createFont(Font.TRUETYPE_FONT,is);
            is = getClass().getResourceAsStream("/resources/fonts/Mario-Kart-DS.ttf");
            cartoon = Font.createFont(Font.TRUETYPE_FONT,is);
        }catch (Exception e){

        }

        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text){

        message= text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(alagard);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();}
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }

    }
    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while (i<gp.player.maxLife/2) {
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+= gp.tileSize +1;
        }

        x = gp.tileSize/2;
        i=0;
        while (i<gp.player.life) {
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i<gp.player.life){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x+= gp.tileSize +1;
        }
    }

    public void drawTitleScreen(){
        g2.setFont(cartoon);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,96F));
        String text = "This be my game";
        int x = getXForCentreText(text);
        int y = gp.tileSize*3;

        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);

        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        // player image
        x = gp.screenWidth/2 - gp.tileSize;
        y += 2*gp.tileSize;
        g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);

        //MENU
        g2.setFont(alagard);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,48F));
        text = "NEW GAME";
        x = getXForCentreText(text);
        y += 4* gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text = "LOAD GAME";
        x = getXForCentreText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text = "QUIT";
        x = getXForCentreText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 2){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }

    public void drawPauseScreen(){
        g2.setFont(cartoon);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        g2.setColor(Color.WHITE);
        String text = "PAUSED";
        int x = getXForCentreText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen(){
        //window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth-gp.tileSize*4;
        int height = gp.tileSize*5;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += gp.tileSize;
        }


    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10,height-10,25,25);

    }
    public int getXForCentreText(String text){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - textLength / 2;
    }
}

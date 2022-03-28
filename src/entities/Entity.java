package entities;

import logic.GamePanel;
import logic.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, stationary;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];



    //STATE
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public int worldX, worldY;
    public String direction = "down";
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;

    //COUNTERS
    public int actionLockCounter=0;
    public int spriteCounter = 0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;

    //CHARACTER STATS
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defence;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;

    //ITEM ATTRIBUTES
    public int attackValue;
    public int defenceValue;
    public String description = "";

    //TYPE
    public int type; // 0 player, 1 npc, 2 monster ,
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_staff = 3;
    public final int type_shield = 4;
    public final int type_consumable = 5;




    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){}
    public void damageReaction() {}
    public void use(Entity entity){}
    public void speak(){
        if(dialogues[dialogueIndex]==null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case"up":
                direction = "down";
                break;
            case"down":
                direction = "up";
                break;
            case"left":
                direction = "right";
                break;
            case"right":
                direction = "left";
                break;
        }
    }
    public void update(){

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer){
            if(!gp.player.invincible){
                gp.playSE(6);

                int damage = attack - gp.player.defence;
                if(damage<0)
                    damage = 0;

                gp.player.life -= damage;

                gp.player.invincible = true;
            }
        }

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(invincible){
            invincibleCounter++;
            if (invincibleCounter >60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX+ gp.tileSize>gp.player.worldX - gp.player.screenX &&
                worldX- gp.tileSize<gp.player.worldX + gp.player.screenX &&
                worldY+ gp.tileSize>gp.player.worldY - gp.player.screenY &&
                worldY- gp.tileSize<gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    break;
                case "down":
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    break;
                case "left":
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    break;
                case "right":
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    break;
            }

            //Monster HP BAR
            if (type == type_monster && hpBarOn) {
                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;
                if(hpBarValue<0){
                    hpBarValue=0;
                }

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1,screenY-16,gp.tileSize+2,12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX,screenY - 15, (int)hpBarValue, 10);

                hpBarCounter++;
                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if(type == type_npc){
                g2.setFont(g2.getFont().deriveFont(26f));
                g2.drawString(name, screenX,screenY-10);
            }

            if (invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                changeOpacity(g2,0.4f);
            }

            if(dying){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            changeOpacity(g2,1f);
        }

    }



    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;
        int i = 5;
        if(dyingCounter <= i){changeOpacity(g2,0f);}
        if(dyingCounter > i   && dyingCounter <= i*2){changeOpacity(g2,1f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*2 && dyingCounter <= i*3){changeOpacity(g2,0f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*3 && dyingCounter <= i*4){changeOpacity(g2,1f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*4 && dyingCounter <= i*5){changeOpacity(g2,0f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*5 && dyingCounter <= i*6){changeOpacity(g2,1f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*6 && dyingCounter <= i*7){changeOpacity(g2,0f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*7 && dyingCounter <= i*8){changeOpacity(g2,1f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if (dyingCounter > 40){
            dying = false;
            alive = false;
        }
    }

    public void changeOpacity(Graphics2D g2, Float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
    }

    public BufferedImage setup(String imagePath,int width,int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/" + imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (Exception e) {
        }
        return image;
    }

}

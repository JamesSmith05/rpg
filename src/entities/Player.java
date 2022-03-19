package entities;

import logic.KeyHandler;
import logic.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean hasBoots;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(12, 16, 24, 28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        hasBoots = false;

        // player stats
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up1 = setup("player/mage_up_1");
        up2 = setup("player/mage_up_2");
        down1 = setup("player/mage_down_1");
        down2 = setup("player/mage_down_2");
        left1 = setup("player/mage_left_1");
        left2 = setup("player/mage_left_2");
        right1 = setup("player/mage_right_1");
        right2 = setup("player/mage_right_2");
    }

    public void update() {

        if ((keyH.upPressed) || (keyH.downPressed) || (keyH.leftPressed) || (keyH.rightPressed)) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            int originalSpeed = speed;

            if (hasBoots && keyH.shiftPressed) {
                speed += 2;
            } else if (keyH.altPressed) {
                speed -= 2;
            }
            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //check events
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

            //if Collision is false player can move
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
            speed = originalSpeed;
        }

        // invinciblilty counter
        if(invincible){
            invincibleCounter++;
            if (invincibleCounter >60){
                invincible = false;
                invincibleCounter = 0;
            }
        }


    }

    public void pickUpObject(int i) {

        if (i != 999) {

        }
    }

    public void interactNPC(int i){
        if (i != 999) {

            if(gp.keyH.enterPressed){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i){
        if (i != 999) {
            if (!invincible){
                life --;
                invincible = true;
            }

        }

    }

    public void draw(Graphics g2) {

        BufferedImage image = null;

        switch (direction) {
            case "stationary":
                image = stationary;
                break;
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        if (invincible){
            // cant make transparant but would
        }

        g2.drawImage(image, screenX, screenY,null);


    }
}

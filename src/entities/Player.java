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

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
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

        up1 = setup("player/mage_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("player/mage_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("player/mage_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("player/mage_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("player/mage_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("player/mage_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("player/mage_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("player/mage_right_2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage(){
        attackUp1 = setup("player/mage_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("player/mage_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("player/mage_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("player/mage_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("player/mage_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("player/mage_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("player/mage_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("player/mage_attack_right_2", gp.tileSize*2, gp.tileSize);

    }

    public void update() {

        if(attacking){
            attack();
        }
        else if ((keyH.upPressed) || (keyH.downPressed) || (keyH.leftPressed) || (keyH.rightPressed) || (keyH.enterPressed)) {
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

            //if Collision is false player can move
            if (!collisionOn && !keyH.enterPressed) {
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

            gp.keyH.enterPressed = false;

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

    public void attack(){

        spriteCounter++;
        if (spriteCounter<= 5){
            spriteNum = 1;
        }
        if(spriteCounter>5 && spriteCounter<=25){
            spriteNum = 2;

            // save current WOLRDX WORLDY SOLIDAREA
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = attackArea.width;
            int solidAreaHeight = attackArea.height;

            //ADJUST FOR ATTACK AREA
            switch (direction){
                case"up": worldY -= attackArea.height; break;
                case"down": worldY += attackArea.height;
                case"left": worldX -= attackArea.width; break;
                case"right": worldX += attackArea.width; break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);


        }

        if(spriteCounter>25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {

        if (i != 999) {

        }
    }

    public void interactNPC(int i){
        if(gp.keyH.enterPressed){
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else {
                attacking = true;
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

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenY = screenY;
        int tempScreenX = screenX;

        switch (direction) {
            case "stationary":image = stationary;break;
            case "up":
                if(!attacking){
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if(attacking){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }

                break;
            case "down":
                if(!attacking){
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if(attacking){
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if(!attacking){
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                if(attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if(!attacking){
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if(attacking){
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break;
        }
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4F));
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null);


        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));


    }
}

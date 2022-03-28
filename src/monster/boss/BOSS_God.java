package monster.boss;

import entities.Entity;
import logic.GamePanel;

import java.util.Random;

public class BOSS_God extends Entity {
    GamePanel gp;

    public BOSS_God(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_largeBoss;
        name = "God";
        speed = 1;
        maxLife = 60;
        life = maxLife;
        attack = 30;
        defence = 5;
        exp = 200;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 144;
        solidArea.height = 144;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage (){
        up1 = setup("monster/GOD", gp.tileSize*3, gp.tileSize*3);
        up2 = setup("monster/GOD", gp.tileSize*3, gp.tileSize*3);
        down1 = setup("monster/GOD", gp.tileSize*3, gp.tileSize*3);
        down2 = setup("monster/GOD", gp.tileSize*3, gp.tileSize*3);
        left1 = setup("monster/GOD", gp.tileSize*3, gp.tileSize*3);
        left2 = setup("monster/GOD", gp.tileSize*3, gp.tileSize*3);
        right1 = setup("monster/GOD", gp.tileSize*3, gp.tileSize*3);
        right2 = setup("monster/GOD", gp.tileSize*3, gp.tileSize*3);
    }

    public void setAction(){

        actionLockCounter++;

        if (actionLockCounter==120){
            Random rand = new Random();
            int i = rand.nextInt(100)+1; // pick rand number

            if (i<= 25){
                direction = "up";
            }
            if (i > 25 && i<= 50){
                direction = "down";
            }
            if (i > 50 && i<= 75){
                direction = "left";
            }
            if (i > 75 && i<= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}

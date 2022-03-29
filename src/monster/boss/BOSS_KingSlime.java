package monster.boss;

import entities.Entity;
import logic.GamePanel;

import java.util.Random;

public class BOSS_KingSlime extends Entity {
    GamePanel gp;

    public BOSS_KingSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_smallBoss;
        name = "KingSlime";
        speed = 1;
        maxLife = 20;
        life = maxLife;
        attack = 8;
        defence = 2;
        exp = 30;

        solidArea.x = 6;
        solidArea.y = 36;
        solidArea.width = 84;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage (){
        up1 = setup("monster/kingSlime_down_1", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("monster/kingSlime_down_2", gp.tileSize*2, gp.tileSize*2);
        down1 = setup("monster/kingSlime_down_1", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("monster/kingSlime_down_2", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("monster/kingSlime_down_1", gp.tileSize*2, gp.tileSize*2);
        left2 = setup("monster/kingSlime_down_2", gp.tileSize*2, gp.tileSize*2);
        right1 = setup("monster/kingSlime_down_1", gp.tileSize*2, gp.tileSize*2);
        right2 = setup("monster/kingSlime_down_2", gp.tileSize*2, gp.tileSize*2);
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


package monster;

import entities.Entity;
import logic.GamePanel;

import java.util.Random;

public class MON_PinkSlime extends Entity {

    GamePanel gp;

    public MON_PinkSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "PinkSlime";
        speed = 1;
        maxLife = 7;
        life = maxLife;
        attack = 5;
        defence = 1;
        exp = 4;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage (){
        up1 = setup("monster/pinkslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/pinkslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/pinkslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/pinkslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/pinkslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/pinkslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/pinkslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/pinkslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){

        actionLockCounter++;

        if (actionLockCounter==90){

            int distanceY = Math.abs(gp.player.worldY - worldY);
            int distanceX = Math.abs(gp.player.worldX - worldX);

            if (distanceY>distanceX && gp.player.worldY < worldY){
                direction = "up";
            }
            if (distanceY>distanceX && gp.player.worldY > worldY){
                direction = "down";
            }
            if ((distanceY<distanceX && gp.player.worldX < worldX)){
                direction = "left";
            }
            if ((distanceY<distanceX && gp.player.worldX > worldX)){
                direction = "right";
            }

            Random rand = new Random();
            int i = rand.nextInt(100)+1; // pick rand number

            if (i<= 5){
                direction = "up";
            }
            if (i > 25 && i<= 30){
                direction = "down";
            }
            if (i > 50 && i<= 55){
                direction = "left";
            }
            if (i > 75 && i<= 80){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}


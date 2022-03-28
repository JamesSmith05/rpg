package entities;

import logic.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        type = type_npc;
        name = "Bill";

        //sets collision area
        solidArea.x = 12;
        solidArea.y = 16;
        solidArea.width = 24;
        solidArea.height = 28;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }
    public void setDialogue(){
        dialogues[0]="Hello, lad.";
        dialogues[1]="You've come here for treasure, eh boy?";
        dialogues[2]="You wont find what ever it is you're looking for.";
        dialogues[3]="What is it you are looking for?";
        dialogues[4]="You know, I used to be something of a great wizard, \nback in my prime that is.";
        dialogues[5]="Could you lend me a hand, \nI think I dropped my glasses.";
        dialogues[6]="Good luck on your adventure my boy.";
        dialogues[7]="1, 2, 3, 4, 5, why are there so many holes, \nin this damned robe!";
        dialogues[8]="You know, my grandson use to play with swords, \nhe was certainly much better than you";
        dialogues[9]="What are you still doing here boy, \nyou wont find any treasure scrounging around here.";

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
    public void speak(){
        super.speak();
    }
}

package object;

import entities.Entity;
import logic.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;
    int value = 4;

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red Potion";
        down1 =setup("objects/potion_Red",gp.tileSize,gp.tileSize);
        description = "["+name+"]\nHeals you by "+value+".";

    }

    public void use(Entity entity){

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the "+name+"!\nYour life has been restored by "+value+".";

        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
    }
}

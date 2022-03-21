package object;

import entities.Entity;
import logic.GamePanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        name = "Wood Shield";
        type= type_shield;
        down1 = setup("objects/shield_Wood",gp.tileSize, gp.tileSize);
        defenceValue = 1;
        description = "[" + name + "]\nJust an old shield";
    }
}

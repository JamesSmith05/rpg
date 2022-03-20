package object;

import entities.Entity;
import logic.GamePanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        name = "Wood Shield";
        down1 = setup("objects/shield_Wood",gp.tileSize, gp.tileSize);
        defenceValue = 1;
        description = "[" + name + "]\nJust and old shield";
    }
}

package object;

import entities.Entity;
import logic.GamePanel;

public class OBJ_Shield_Iron extends Entity {
    public OBJ_Shield_Iron(GamePanel gp) {
        super(gp);
        name = "Iron Shield";
        type= type_shield;
        down1 = setup("objects/shield_Iron",gp.tileSize, gp.tileSize);
        defenceValue = 2;
        description = "[" + name + "]\nJust an Iron shield";
    }
}
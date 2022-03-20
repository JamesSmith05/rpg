package object;

import entities.Entity;
import logic.GamePanel;

public class OBJ_Staff_Fire extends Entity {


    public OBJ_Staff_Fire(GamePanel gp) {
        super(gp);

        name = "Fire Staff";

        down1 = setup("objects/staff_Fire", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nA staff\nit has fire attributes!";
    }
}

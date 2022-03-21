package object;

import entities.Entity;
import logic.GamePanel;

public class OBJ_Staff_Fire extends Entity {


    public OBJ_Staff_Fire(GamePanel gp) {
        super(gp);

        name = "Fire Staff";

        type= type_staff;
        down1 = setup("objects/staff_Fire", gp.tileSize, gp.tileSize);
        attackValue = 3;
        attackArea.width = 48;
        attackArea.height = 48;

        description = "[" + name + "]\nit has fire attributes!";
    }
}

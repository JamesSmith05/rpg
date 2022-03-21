package object;

import entities.Entity;
import logic.GamePanel;

public class OBJ_Staff_Ice extends Entity {
    public OBJ_Staff_Ice(GamePanel gp) {
        super(gp);
        name = "Ice Staff";
        type= type_staff;
        down1 = setup("objects/staff_Ice",gp.tileSize,gp.tileSize);

        attackArea.width = 48;
        attackArea.height = 48;
        attackValue = 2;

        description = "[" + name + "] \nit has fire attributes!";
    }
}

package object;

import entities.Entity;
import logic.GamePanel;

public class OBJ_Staff_Basic extends Entity {


    public OBJ_Staff_Basic(GamePanel gp) {
        super(gp);

        name = "Basic Staff";

        type= type_staff;
        down1 = setup("objects/staff_Basic", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 48;
        attackArea.height = 48;

        description = "[" + name + "]\nit has no attributes!";
    }
}

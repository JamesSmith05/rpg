package object;

import entities.Entity;
import logic.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp){

        super(gp);

        name = "Key";
        down1 = setup("objects/key", gp.tileSize, gp.tileSize);

        description = "[" + name + "]\nIt might open something...";

    }

}

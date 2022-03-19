package object;

import entities.Entity;
import logic.GamePanel;
public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        image = setup("objects/heart_Full", gp.tileSize, gp.tileSize);
        image2 = setup("objects/heart_Half", gp.tileSize, gp.tileSize);
        image3 = setup("objects/heart_Blank", gp.tileSize, gp.tileSize);
    }
}

package object;

import logic.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject{

    GamePanel gp;

    public OBJ_Heart(GamePanel gp){
        this.gp = gp;
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/heart_Full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/heart_Half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/heart_Blank.png"));
            image = uTool.scaleImage(image,gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2,gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3,gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

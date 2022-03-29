package object;

import entities.Projectile;
import logic.GamePanel;

public class OBJ_Fireball extends Projectile {
    GamePanel gp;
    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name  = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = setup("projectiles/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("projectiles/fireball_up_2", gp.tileSize, gp.tileSize);
        upR1 = setup("projectiles/fireball_upR_1", gp.tileSize, gp.tileSize);
        upR2 = setup("projectiles/fireball_upR_2", gp.tileSize, gp.tileSize);
        upL1 = setup("projectiles/fireball_upL_1", gp.tileSize, gp.tileSize);
        upL2 = setup("projectiles/fireball_upL_2", gp.tileSize, gp.tileSize);

        down1 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("projectiles/fireball_down_2", gp.tileSize, gp.tileSize);
        downR1 = setup("projectiles/fireball_downR_1", gp.tileSize, gp.tileSize);
        downR2 = setup("projectiles/fireball_downR_2", gp.tileSize, gp.tileSize);
        downL1 = setup("projectiles/fireball_downL_1", gp.tileSize, gp.tileSize);
        downL2 = setup("projectiles/fireball_downL_2", gp.tileSize, gp.tileSize);

        left1 = setup("projectiles/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("projectiles/fireball_left_2", gp.tileSize, gp.tileSize);

        right1 = setup("projectiles/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("projectiles/fireball_right_2", gp.tileSize, gp.tileSize);

    }
}

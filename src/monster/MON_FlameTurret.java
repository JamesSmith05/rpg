package monster;

import entities.Entity;
import entities.Projectile;
import logic.GamePanel;
import object.OBJ_Fireball;

public class MON_FlameTurret extends Entity {

    GamePanel gp;

    public Projectile projectile2,projectile3,projectile4,projectile5,projectile6,projectile7,projectile8;

    public MON_FlameTurret(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "GreenSlime";
        speed = 0;
        maxLife = 12;
        life = maxLife;
        attack = 4;
        defence = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        projectile = new OBJ_Fireball(gp);
        projectile2 = new OBJ_Fireball(gp);
        projectile3 = new OBJ_Fireball(gp);
        projectile4 = new OBJ_Fireball(gp);
        projectile5 = new OBJ_Fireball(gp);
        projectile6 = new OBJ_Fireball(gp);
        projectile7 = new OBJ_Fireball(gp);
        projectile8 = new OBJ_Fireball(gp);

        getImage();
    }

    public void getImage(){
        up1 = setup("projectiles/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("projectiles/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("projectiles/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("projectiles/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("projectiles/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("projectiles/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("projectiles/fireball_right_2", gp.tileSize, gp.tileSize);

    }

    public void setAction(){

        if(shotAvailableCounter == 280){
            if(!projectile.alive){
                projectile.set(worldX,worldY,"up",true,this);gp.projectileList.add(projectile);
            }if(!projectile2.alive){
                projectile2.set(worldX,worldY,"upR",true,this);gp.projectileList.add(projectile2);
            }if(!projectile3.alive){
                projectile3.set(worldX,worldY,"upL",true,this);gp.projectileList.add(projectile3);
            }if(!projectile4.alive){
                projectile4.set(worldX,worldY,"left",true,this);gp.projectileList.add(projectile4);
            }if(!projectile5.alive){
                projectile5.set(worldX,worldY,"right",true,this);gp.projectileList.add(projectile5);
            }if(!projectile6.alive){
                projectile6.set(worldX,worldY,"down",true,this);gp.projectileList.add(projectile6);
            }if(!projectile7.alive){
                projectile7.set(worldX,worldY,"downR",true,this);gp.projectileList.add(projectile7);
            }if(!projectile8.alive){
                projectile8.set(worldX,worldY,"downL",true,this);gp.projectileList.add(projectile8);
            }
            shotAvailableCounter = 0;

            gp.playSE(7);
        }

        if(shotAvailableCounter < 280){
            shotAvailableCounter++;
        }



    }

}

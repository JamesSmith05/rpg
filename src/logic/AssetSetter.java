package logic;

import entities.NPC_OldMan;
import monster.MON_FlameTurret;
import monster.MON_GreenSlime;
import monster.MON_PinkSlime;
import monster.boss.BOSS_God;
import monster.boss.BOSS_KingSlime;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        int i = 0;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize*25;
        gp.obj[i].worldY = gp.tileSize*23;
        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize*21;
        gp.obj[i].worldY = gp.tileSize*19;
        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize*26;
        gp.obj[i].worldY = gp.tileSize*21;
        i++;
        gp.obj[i] = new OBJ_Staff_Ice(gp);
        gp.obj[i].worldX = gp.tileSize*33;
        gp.obj[i].worldY = gp.tileSize*21;
        i++;
        gp.obj[i] = new OBJ_Staff_Fire(gp);
        gp.obj[i].worldX = gp.tileSize*18;
        gp.obj[i].worldY = gp.tileSize*21;
        i++;
        gp.obj[i] = new OBJ_Shield_Iron(gp);
        gp.obj[i].worldX = gp.tileSize*35;
        gp.obj[i].worldY = gp.tileSize*21;
        i++;
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = gp.tileSize*22;
        gp.obj[i].worldY = gp.tileSize*27;

    }
    public void setNPC(){

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;

    }

    public void setMonster(){

        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*21;
        gp.monster[i].worldY = gp.tileSize*38;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*23;
        gp.monster[i].worldY = gp.tileSize*42;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*24;
        gp.monster[i].worldY = gp.tileSize*37;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*34;
        gp.monster[i].worldY = gp.tileSize*42;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*38;
        gp.monster[i].worldY = gp.tileSize*42;
        i++;
        gp.monster[i] = new MON_PinkSlime(gp);
        gp.monster[i].worldX = gp.tileSize*39;
        gp.monster[i].worldY = gp.tileSize*42;
        i++;
        gp.monster[i] = new BOSS_KingSlime(gp);
        gp.monster[i].worldX = gp.tileSize*36;
        gp.monster[i].worldY = gp.tileSize*10;
        i++;
        gp.monster[i] = new BOSS_God(gp);
        gp.monster[i].worldX = gp.tileSize*10;
        gp.monster[i].worldY = gp.tileSize*31;
        i++;
        gp.monster[i] = new MON_FlameTurret(gp);
        gp.monster[i].worldX = gp.tileSize*14;
        gp.monster[i].worldY = gp.tileSize*21;
    }
}

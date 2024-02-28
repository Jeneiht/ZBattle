package com.trongthien.zBattle.GameMap;
import com.trongthien.zBattle.character.Player;
import com.trongthien.zBattle.constant.GameConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Camera {
    private Player player;
    private GameMap gameMap;
    private int x, y;
    public Camera(Player player, GameMap gameMap) {
        this.player = player;
        this.gameMap = gameMap;
        update();
    }
    public void update() {
        x= player.x+ GameConstant.tileSize/2-GameConstant.screenWidth/2;
        y= player.y+GameConstant.tileSize/2-GameConstant.screenHeight/2;
        if(x<0) x=0;
        if(y<0) y=0;
        if(x+ GameConstant.screenWidth>gameMap.width){
            x=gameMap.width- GameConstant.screenWidth;
        }
        if(y+ GameConstant.screenHeight>gameMap.height){
            y=gameMap.height- GameConstant.screenHeight;
        }
    }
}

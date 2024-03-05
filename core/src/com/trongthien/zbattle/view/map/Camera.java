package com.trongthien.zbattle.view.map;

import com.trongthien.zbattle.model.Player;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.view.map.GameMap;
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
        x = player.getX() + GameConstant.tileSize / 2 - GameConstant.screenWidth / 2;
        y = player.getY() + GameConstant.tileSize / 2 - GameConstant.screenHeight / 2;
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x + GameConstant.screenWidth > gameMap.width) {
            x = gameMap.width - GameConstant.screenWidth;
        }
        if (y > gameMap.height) {
            y = gameMap.height - GameConstant.screenHeight;
        }
    }
}

package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.inventory.Item;

import java.util.List;

public class GameLogic {
    private GameMap map;

    public GameLogic() {
        this.map = MapLoader.loadMap();
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public String getPlayerDamage() {
        return Integer.toString(map.getPlayer().getAttackPoint());
    }

    public String getInventory() {
        List<String> items = map.getPlayer().getItems();
        return String.join("\n", items);
    }

    public String getKillCount () {
        return Integer.toString(map.getPlayer().getKillCount());
    }

    public GameMap getMap() {
        return map;
    }
}

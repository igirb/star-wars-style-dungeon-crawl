package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class Yoda extends Friend{
    public Yoda(Cell cell, GameMap map) {
        super(cell, map);
    }

    @Override
    boolean canMove(int dx, int dy) {
        return false;
    }

    @Override
    public String getTileName() {
        return "yoda";
    }
}

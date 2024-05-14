package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;

public abstract class Enemy extends Actor{
    public Enemy(Cell cell, GameMap map) {

        super(cell, map);
    }

    protected abstract void behaviour();
}

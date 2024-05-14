package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public abstract class Friend extends Actor{
    public Friend(Cell cell, GameMap map) {
        super(cell, map);
    }
}
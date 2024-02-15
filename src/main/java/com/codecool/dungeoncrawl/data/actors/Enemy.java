package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class Enemy extends Actor{
    public Enemy(Cell cell) {
        super(cell);
    }

    protected abstract void behaviour();
    protected boolean canMove(int dx, int dy){
        Cell nextCell = getCell().getNeighbor(dx, dy);
        return nextCell.isPassable() && nextCell.getActor() == null;
    }
}

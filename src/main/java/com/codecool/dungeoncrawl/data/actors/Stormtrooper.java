package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import java.util.Random;

public class Stormtrooper extends Enemy {
    public Stormtrooper(Cell cell, GameMap map) {
        super(cell, map);
        health = 20;
        damage = 2;
    }


    @Override
    protected void behaviour() {
        int dx, dy;
        Random random = new Random();
        dx = random.nextInt(3 + 3) - 3;
        dy = random.nextInt(3 + 3) - 3;
        if (isMoveWithinMap(dx, dy) && canMove(dx, dy)) {

            move(dx, dy);
        }
    }

//OLD
//    @Override
//    public boolean canMove(int dx, int dy) {
//        Cell nextCell = getCell().getNeighbor(dx, dy);
//
//        return nextCell.isPassable() && nextCell.getActor() == null;
//    }

    @Override
    public boolean canMove(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        return nextCell.getType() == CellType.FLOOR
                || nextCell.getType() == CellType.OPENED_DOOR
                && nextCell.getActor() == null;
    }


    @Override
    public String getTileName() {
        return "stormtrooper";
    }
}

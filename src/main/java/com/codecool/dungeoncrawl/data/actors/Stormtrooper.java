package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import java.util.Random;

public class Stormtrooper extends Enemy {
    public Stormtrooper(Cell cell) {
        super(cell);
        health = 20;
        damage = 2;
    }

    private boolean canMove(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        return nextCell.isPassable() && nextCell.getActor() == null;
    }

    @Override
    protected void behaviour() {
        Random random = new Random();
        int dx, dy;
        dx = random.nextInt(3 + 3) - 3;
        dy = random.nextInt(3 + 3) - 3;
        if (canMove(dx, dy)) {
            move(dx, dy);
        }
    }

    @Override
    public String getTileName() {
        return "stormtrooper";
    }
}

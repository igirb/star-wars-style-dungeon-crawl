package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;

import java.util.Random;

public class Stormtrooper extends Enemy {
    public Stormtrooper(Cell cell) {
        super(cell);
        health = 20;
        damage = 2;
    }


    @Override
    protected void behaviour() {
        Random random = new Random();
        int dx, dy;
        dx = random.nextInt(5);
        dy = random.nextInt(5);
        if (canMove(dx, dy)) {
            move(dx, dy);
        }
    }

    @Override
    public String getTileName() {
        return "stormtrooper";
    }
}

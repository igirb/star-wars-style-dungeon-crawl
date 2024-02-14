package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
        this.health = 10;
        this.damage = 2;
    }

    //enemy abstract class es abbol jon a Skeleton

    @Override
    public String getTileName() {
        return "skeleton";
    }
}

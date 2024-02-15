package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Yoda extends Friend{
    public Yoda(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "yoda";
    }
}

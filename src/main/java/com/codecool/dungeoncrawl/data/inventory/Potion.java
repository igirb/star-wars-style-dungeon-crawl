package com.codecool.dungeoncrawl.data.inventory;

import com.codecool.dungeoncrawl.data.Cell;

public class Potion extends Equipment {

    public Potion(String name, Cell cell, int healthRegen) {
        super(name, cell, healthRegen);
    }

    @Override
    public String getTileName() {
        return "potion";
    }
}

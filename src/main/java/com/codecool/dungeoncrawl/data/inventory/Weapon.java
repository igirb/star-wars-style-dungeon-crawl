package com.codecool.dungeoncrawl.data.inventory;

import com.codecool.dungeoncrawl.data.Cell;

public class Weapon extends Equipment {

    public Weapon(String name, Cell cell, int damage) {
        super(name, cell, damage);
    }



    @Override
    public String getTileName() {
        return "weapon";
    }

    @Override
    public String toString() {
        return "Weapon: " + this.name;
    }
}

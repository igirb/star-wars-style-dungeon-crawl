package com.codecool.dungeoncrawl.data.inventory;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class Equipment extends Item{
    private int value;

    public Equipment(String name, Cell cell, int value) {
        super(name, cell);
        this.value = value;
    }
}

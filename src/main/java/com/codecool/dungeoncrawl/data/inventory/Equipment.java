package com.codecool.dungeoncrawl.data.inventory;

public abstract class Equipment extends Item{
    private int value;

    public Equipment(String name, int value) {
        super(name);
        this.value = value;
    }
}

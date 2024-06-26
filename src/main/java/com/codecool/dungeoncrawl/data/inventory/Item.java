package com.codecool.dungeoncrawl.data.inventory;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    protected String name;

    public Item(String name, Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Cell getCell() {
        return cell;
    }
}


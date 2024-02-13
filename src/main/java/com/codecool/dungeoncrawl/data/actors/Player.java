package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.inventory.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();
    public Player(Cell cell) {
        super(cell);
        this.health = 15;
        this.damage = 9; //to be calibrated
    }

    public String getTileName() {
        return "player";
    }

    private void restoreHP(int value){
        //max hp
        health += value;
    }

    private void addItem(){};

    private void removeItem(){};

    public void findItem(Item item){}

}

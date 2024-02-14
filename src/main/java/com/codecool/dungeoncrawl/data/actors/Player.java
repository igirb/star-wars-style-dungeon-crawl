package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.inventory.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();
    public Player(Cell cell) {
        super(cell);
        this.health = 15;
        this.damage = 9; //to be calibrated
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        if (cell.getItem() != null) {
            pickUpItem(cell.getItem());
        }
        System.out.println(inventory);

    }

    public String getTileName() {
        return "player";
    }

    private void restoreHP(int value){
        //max hp
        health += value;
    }

    public void pickUpItem(Item item){
        inventory.add(item);
    };

    private void removeItem(){};

    public void findItem(Item item){}

}
package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.inventory.Item;
import com.codecool.dungeoncrawl.data.inventory.Potion;
import com.codecool.dungeoncrawl.data.inventory.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();
    private final int maxHealth;
    public Player(Cell cell) {
        super(cell);
        this.health = 15;
        this.damage = 9; //to be calibrated
        this.maxHealth = health;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        Item foundItem = cell.getItem();
        if (foundItem != null) {
            getItemStat(foundItem);
            pickUpItem(cell.getItem());

            cell.setItem(null);
        }
        System.out.println(inventory);

    }

    private void getItemStat(Item foundItem) {
        if(foundItem instanceof Weapon){
            increaseStat("damage", ((Weapon) foundItem).getValue());
            System.out.println(damage);
        } else if (foundItem instanceof Potion) {
            increaseStat("health", ((Potion) foundItem).getValue());
        }
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

    private void removeItem(Item item){
        inventory.remove(item);
    };

    public boolean findItem(Item item){
        return inventory.contains(item);
    }

    private void increaseStat(String stat, int value){
        switch (stat){
            case "damage"-> damage += value;
            case "health"-> health = value;
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
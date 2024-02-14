package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.inventory.Item;
import com.codecool.dungeoncrawl.data.inventory.Potion;
import com.codecool.dungeoncrawl.data.inventory.Weapon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player extends Actor {

    private List<Item> inventory = new ArrayList<>();
    private int killCount;

    private final int maxHealth;
    public Player(Cell cell) {
        super(cell);
        this.health = 15;
        this.damage = 9; //to be calibrated
        killCount = 0;
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

    private void restoreHP(int value) {
        //max hp
        health += value;
    }

    public void pickUpItem(Item item) {
        inventory.add(item);
    }

    public void incrementKillCount() {
        killCount++;
    }

    public int getKillCount() {
        return killCount;
    }

    public List<String> getItems() {
        Map<String, List<String>> groupedItems = inventory.stream()
                .sorted(Comparator.comparing(item -> item.getClass().getSimpleName()))
                .collect(Collectors.groupingBy(
                        item -> item.getClass().getSimpleName() + ":",//key of map
                        Collectors.mapping(this::formatItem, Collectors.toList()) //value of map
                ));

        return groupedItems.entrySet().stream()
                .flatMap(entry -> Stream.concat(Stream.of(entry.getKey()), entry.getValue().stream()))
                .collect(Collectors.toList());
    }

    private String formatItem(Item item) {
        if (item instanceof Weapon weapon) {
            return weapon.getName() + " (+" + weapon.getValue() + " AD)";
        } else if(item instanceof Potion potion) {
            return potion.getName() + " (" + potion.getValue() + "restore HP)";
        }else {
            return item.getName();
        }
    }

    public void incrementEliminationCount() {

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
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
        this.maxHealth = 15;
        this.damage = 9; //to be calibrated
        killCount = 0;
        this.health = maxHealth;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        Item foundItem = cell.getItem();
        if (foundItem != null) {
            pickUpItem(cell.getItem());
            cell.setItem(null);
        }
        System.out.println(inventory);
    }

    public String getTileName() {
        return "player";
    }

    private void restoreHP(int value) {
        //max hp
        health += value;
    }

    private void pickUpItem(Item item) {
        inventory.add(item);
        if (item instanceof Weapon) {
            increaseStat("damage", ((Weapon) item).getValue());
        }
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

    private void removeItem(Item item){
        inventory.remove(item);
    };

    private Item findPotion(){
        return inventory.stream().filter(item -> item instanceof Potion).findFirst().orElse(null);
    }

    private void increaseStat(String stat, int value){
        switch (stat){
            case "damage"-> damage += value;
            case "health"-> health = maxHealth;
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void usePotion(){
        increaseStat("health", maxHealth);
        removeItem(findPotion());
    }

}
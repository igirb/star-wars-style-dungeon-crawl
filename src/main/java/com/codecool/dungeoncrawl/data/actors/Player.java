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

    public Player(Cell cell) {
        super(cell);
        health = 15;
        damage = 9; //to be calibrated
        killCount = 0;
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

    private void restoreHP(int value) {
        //max hp
        health += value;
    }

    public void pickUpItem(Item item) {
        inventory.add(item);
    }

    ;

    private void removeItem() {
    }


    public void findItem(Item item) {
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

    }
}
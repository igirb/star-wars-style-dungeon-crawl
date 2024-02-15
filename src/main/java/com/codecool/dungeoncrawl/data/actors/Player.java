package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
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

    public Player(Cell cell, GameMap map) {
        super(cell, map);
        maxHealth = 15;
        damage = 9; //to be calibrated
        killCount = 0;
        health = maxHealth;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getTileName() {
        return "player";
    }

    private void restoreHP(int value) {
        //max hp
        health += value;
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

    private void getItemStat(Item foundItem) {
        if (foundItem instanceof Weapon) {
            increaseStat("damage", ((Weapon) foundItem).getValue());
            System.out.println(damage);
        } else if (foundItem instanceof Potion) {
            increaseStat("health", ((Potion) foundItem).getValue());
        }
    }

    public void incrementKillCount() {
        killCount++;
    }

    public void pickUpItem(Item item) {
        inventory.add(item);
    }

    private void removeItem(Item item) {
        inventory.remove(item);
    }

    public boolean findItem(Item item) {
        return inventory.contains(item);
    }

    private void increaseStat(String stat, int value) {
        switch (stat) {
            case "damage" -> damage += value;
            case "health" -> health = value;
        }
    }

    private String formatItem(Item item) {
        if (item instanceof Weapon weapon) {
            return weapon.getName() + " (+" + weapon.getValue() + " AD)";
        } else if (item instanceof Potion potion) {
            return potion.getName() + " (" + potion.getValue() + "restore HP)";
        } else {
            return item.getName();
        }
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (isMoveWithinMap(dx, dy) && canMove(dx, dy)) {
            Actor actor = nextCell.getActor();
            if (actor instanceof Enemy) {
                this.attack(nextCell, actor);
            } else if (this.health > 0) {
                super.move(dx, dy);
                Item foundItem = cell.getItem();
                if (foundItem != null) {
                    getItemStat(foundItem);
                    pickUpItem(cell.getItem());

                    cell.setItem(null);
                }
                System.out.println(inventory);
            }
        }
    }

    @Override
    boolean canMove(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        return nextCell.getType() == CellType.FLOOR
                || nextCell.getType() == CellType.OPENED_DOOR;
    }
}
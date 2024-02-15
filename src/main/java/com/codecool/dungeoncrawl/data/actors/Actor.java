package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.logic.Game;

public abstract class Actor implements Drawable {

    protected GameMap map;
    protected Cell cell;
    protected int health;
    protected int damage;

    public Actor(Cell cell, GameMap map) {
        this.cell = cell;
        this.cell.setActor(this);
        this.map = map;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPoint() {
        return damage;
    }

    public void setAttackPoint(int damage) {
        this.damage = damage;
    }

    public Cell getCell() {
        return cell;
    }

    public GameMap getMap() {
        return map;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

//    public void move(int dx, int dy) {
//        Cell nextCell = cell.getNeighbor(dx, dy);
//        if (isMoveWithinMap(dx, dy) && nextCell.isPassable()) {
//            Actor actor = nextCell.getActor();
//            if (actor instanceof Enemy) {
//                this.attack(nextCell, actor);
//            } else if (this.health > 0) {
//                cell.setActor(null);
//                nextCell.setActor(this);
//                cell = nextCell;
//            }
//        }
//    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public void attack(Cell enemyPosition, Actor opponent) {
        opponent.looseHP(this.getAttackPoint());
        if (opponent.getHealth() <= 0) {
            enemyPosition.setActor(null);
        } else {
            this.looseHP(opponent.getAttackPoint());
            ((Enemy) opponent).behaviour();
        }
    }

    public void looseHP(int damage) {
        this.health -= damage;

        if (this.health <= 0) {
            this.health = 0;
            setIfActorIsKilled();
        }
    }

    public void setIfActorIsKilled() {
        if (this instanceof Player) {
            System.exit(0);
        } else if (this.getHealth() <= 0) {
            cell.setActor(null);
        }
    }

    //TO DO check for same location?
    boolean isMoveWithinMap(int dx, int dy) {
        int targetX = getX() + dx;
        int targetY = getY() + dy;
        System.out.println(getX());
        System.out.println(getY());
//        Cell nextCell = cell.getNeighbor(dx, targetY);
//
//        if (getX() == dx && getY() == targetY) {
//            return true;
//        }

        return (targetX >= 0) && (targetX < map.getWidth()) && (targetY >= 0) && (targetY < map.getHeight());
    }

    abstract boolean canMove(int dx, int dy);
}

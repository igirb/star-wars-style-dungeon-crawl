package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.GameMap;

public abstract class Actor implements Drawable {

    protected Cell cell;
    protected int health;
    protected int damage;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
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

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.isPassable()) {
            Actor actor = nextCell.getActor();
            if (actor instanceof Enemy) {
                this.attack(nextCell, actor);
            } else if (this.health > 0){
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
        }
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
            setIfPlayerIsKilled();
        }
    }

    public void setIfPlayerIsKilled() {
        if (this instanceof Player && this.getHealth() <= 0) {
            cell.setActor(null);
            System.out.println("Game Over");
        }
    }
}

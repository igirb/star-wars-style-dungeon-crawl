package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class DarthSidious extends Enemy{

    public DarthSidious(Cell cell, GameMap map) {
        super(cell, map);
        health = 40;
        damage = 5;
        startMoving();
    }

    @Override
    boolean canMove(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        return nextCell.getType() == CellType.FLOOR
                || nextCell.getType() == CellType.OPENED_DOOR
                && nextCell.getActor() == null;
    }

    @Override
    public String getTileName() {
        return "darthsidious";
    }

    private void startMoving(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                behaviour();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    @Override
    protected void behaviour() {
        int[] dxMoves = {0, 0, -1, 1};
        int[] dyMoves = {-1, 1, 0, 0};
        Random random = new Random();
        int randomDirection = random.nextInt(4);

        int newX = dxMoves[randomDirection];
        int newY = dyMoves[randomDirection];
        if(canMove(newX, newY)){
            move(newX, newY);
        }
    }
}

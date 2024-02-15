package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;



public class DarthSidious extends Enemy{
    public DarthSidious(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        int[] dxMoves = {0, 0, -1, 1};
        int[] dyMoves = {-1, 1, 0, 0};
        Random random = new Random();
        int randomDirection = random.nextInt(4);

        int newX = getX() + dxMoves[randomDirection];
        int newY = getY() + dyMoves[randomDirection];
        Cell nextCell = cell.getNeighbor(newX, newY);

        if(nextCell.isPassable()){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public String getTileName() {
        return "darthsidious";
    }

    @Override
    protected void behaviour() {

    }
}

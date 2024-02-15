package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Player;

public class GameMap {

    private int width;
    private int height;
    private Cell[][] cells;
    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY) {

        if (currentX == targetX && currentY == targetY) {
            return true;
        }

        boolean withinMap = currentX >= 0 && currentX < width && currentY >= 0 && currentY < height;
        if (withinMap) {
            boolean isNotRestricted = cells[currentX][currentY].getType() != CellType.WALL
                    && cells[currentX][currentY].getType() != CellType.CLOSED_DOOR;

            if (isNotRestricted) {
                // Up
                if (isValidMove(currentX, currentY - 1, targetX, targetY)) {
                    return true;
                }
                // Down
                if (isValidMove(currentX, currentY + 1, targetX, targetY)) {
                    return true;
                }
                // Left
                if (isValidMove(currentX - 1, currentY, targetX, targetY)) {
                    return true;
                }
                // Right
                if (isValidMove(currentX + 1, currentY, targetX, targetY)) {
                    return true;
                }
            }
        }

        return false;
    }
}

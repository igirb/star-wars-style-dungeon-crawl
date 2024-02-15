package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.DarthSidious;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Stormtrooper;
import com.codecool.dungeoncrawl.data.actors.Yoda;
import com.codecool.dungeoncrawl.data.inventory.Key;
import com.codecool.dungeoncrawl.data.inventory.Potion;
import com.codecool.dungeoncrawl.data.inventory.Weapon;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'd':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Stormtrooper(cell, map);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell, map));
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key("Door key", cell);
                            break;
                        case 'l':
                            cell.setType(CellType.FLOOR);
                            new Weapon("Light saber", cell, 5);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Potion("Focus Tonics", cell, map.getPlayer().getMaxHealth());
                            break;

                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new DarthSidious(cell, map);
                            break;

                        case 'y':
                            cell.setType(CellType.FLOOR);
                            new Yoda(cell, map);

                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}

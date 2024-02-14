package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Jump implements KeyHandler{
    public static final KeyCode code = KeyCode.UP;
    public static final KeyCode code2 = KeyCode.RIGHT;
    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code.equals(event.getCode()) && event.isShiftDown())
            map.getPlayer().move(0, -2);
        else if (code2.equals(event.getCode()) && event.isShiftDown()) {
            map.getPlayer().move(2, 0);
        }
    }
}

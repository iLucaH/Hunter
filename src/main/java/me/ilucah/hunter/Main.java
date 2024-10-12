package me.ilucah.hunter;

import me.ilucah.hunter.handler.Game;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Game game = new Game("Hunter 2D", Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        game.start();
    }

}
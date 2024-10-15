package me.ilucah.hunter;

import me.ilucah.hunter.handler.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game("Hunter 2D", 800, 800);
        game.start();
    }

}
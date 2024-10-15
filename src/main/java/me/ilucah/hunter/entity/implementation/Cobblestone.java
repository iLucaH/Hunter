package me.ilucah.hunter.entity.implementation;

import me.ilucah.hunter.entity.Entity;
import me.ilucah.hunter.entity.Interactable;
import me.ilucah.hunter.entity.Permeable;
import me.ilucah.hunter.entity.implementation.moving.Player;
import me.ilucah.hunter.input.KeyInteract;
import me.ilucah.hunter.input.MouseInteract;

import java.awt.*;

public class Cobblestone extends Entity implements Permeable, Interactable {

    public Cobblestone(int x, int y, int size) {
        super(x, y, size, 1, true);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(128, 128, 128));
        g.fillRect(x, y, width, height);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void onClick(MouseInteract click) {

    }

    @Override
    public void onKey(KeyInteract key) {

    }

    @Override
    public void onPlayerCollision(int playerX, int playerY) {

    }

    @Override
    public void onPlayerInteract(Player p, int interactX, int interactY) {

    }
}

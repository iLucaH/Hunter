package me.ilucah.hunter.entity.implementation.moving;

import me.ilucah.hunter.entity.Entity;

import java.awt.*;

public class Bug extends Entity {

    private Point nextRandomPoint;

    public Bug(int x, int y, int id) {
        super(x, y, 64, 100, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(x, y, width, height);
    }

}

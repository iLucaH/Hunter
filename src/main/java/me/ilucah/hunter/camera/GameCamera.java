package me.ilucah.hunter.camera;

import me.ilucah.hunter.entity.Entity;
import me.ilucah.hunter.handler.Handler;

import java.util.List;

public class GameCamera {

    private Handler handler;

    private int xOffset, yOffset;

    private int cachedXOffset, cachedYOffset;

    public GameCamera(Handler handler) {
        this.handler = handler;
        xOffset = 0;
        yOffset = 0;
        cachedXOffset = 0;
        cachedYOffset = 0;
    }

    public void tick(List<Entity> entityList) {
        for (Entity entity : entityList) {
            entity.setX(entity.getX() + xOffset);
            entity.setY(entity.getY() + yOffset);
        }
        cachedXOffset = xOffset;
        cachedYOffset = yOffset;
        xOffset = 0;
        yOffset = 0;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public void addXOffset(int amount) {
        xOffset += amount;
    }

    public void addYOffset(int amount) {
        yOffset += amount;
    }

    public void subtractXOffset(int amount) {
        xOffset -= amount;
    }

    public void subtractYOffset(int amount) {
        yOffset -= amount;
    }

    public int getCachedXOffset() {
        return cachedXOffset;
    }

    public int getCachedYOffset() {
        return cachedYOffset;
    }
}

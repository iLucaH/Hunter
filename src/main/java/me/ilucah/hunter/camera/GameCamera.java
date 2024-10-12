package me.ilucah.hunter.camera;

import me.ilucah.hunter.handler.Handler;

public class GameCamera {

    private Handler handler;

    private int xOffset, yOffset;

    public GameCamera(Handler handler) {
        this.handler = handler;
        xOffset = 0;
        yOffset = 0;
    }

    public void tick() {
        xOffset = 0;
        yOffset = 0;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getyOffset() {
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

}

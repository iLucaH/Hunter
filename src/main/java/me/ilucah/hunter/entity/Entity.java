package me.ilucah.hunter.entity;

import me.ilucah.hunter.camera.GameCamera;
import me.ilucah.hunter.input.MouseInteract;
import me.ilucah.hunter.input.MouseManager;
import me.ilucah.hunter.scene.implementation.gamescene.GameScene;

import java.awt.*;

public abstract class Entity {

    protected int x, y, width, height;
    private final int id;
    private boolean solid;

    public Entity(int x, int y, int size, int id, boolean solid) {
        this(x, y, size, size, id);
        this.solid = solid;
    }

    public Entity(int x, int y, int width, int height, int id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void tick(GameScene scene) {
        if (this instanceof Interactable) {
            MouseManager mouseManager = scene.getHandler().getMouseManager();
            if (mouseManager.getMouseX() >= x && mouseManager.getMouseX() <= x + width && mouseManager.getMouseY() >= y && mouseManager.getMouseY() <= y + height) {
                // onClick
                if (mouseManager.isLeftPressed()) {
                    ((Interactable) this).onClick(MouseInteract.LEFT);
                }
                if (mouseManager.isRightPressed()) {
                    ((Interactable) this).onClick(MouseInteract.RIGHT);
                }
            }
        }
        if (this instanceof Permeable) {

        }
        tick();
    }

    public int getId() {
        return id;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

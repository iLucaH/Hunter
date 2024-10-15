package me.ilucah.hunter.entity.implementation.moving;

import me.ilucah.hunter.entity.Entity;
import me.ilucah.hunter.entity.Interactable;
import me.ilucah.hunter.entity.Permeable;
import me.ilucah.hunter.fx.implementation.BleedEffect;
import me.ilucah.hunter.fx.implementation.LineSweepEffect;
import me.ilucah.hunter.fx.implementation.OverlayFlickerEffect;
import me.ilucah.hunter.input.KeyInteract;
import me.ilucah.hunter.input.KeyManager;
import me.ilucah.hunter.input.MouseInteract;
import me.ilucah.hunter.scene.implementation.gamescene.GameScene;

import java.awt.*;
import java.util.List;

public class Player extends Entity implements Permeable, Interactable {

    private final GameScene scene;

    float speed = 2;
    float hearts = 20;
    float maxHealth = 20;

    public Player(GameScene scene, int x, int y) {
        super(x, y, 62, 62, 999);
        this.x -= width / 2;
        this.y += width / 2;
        this.scene = scene;
    }

    @Override
    public void tick() {
        handleMovement();

        if (scene.getHandler().getMouseManager().isLeftReleased()) {
            if (OverlayFlickerEffect.existsEffect != null)
                return;
            LineSweepEffect effect = new LineSweepEffect(scene, scene.getPlayer());
            scene.getLevelManager().getLevel().addAnimation(effect);
            scene.getLevelManager().getLevel().addAnimation(new OverlayFlickerEffect(scene.getHandler().getWidth(), scene.getHandler().getHeight(), effect));
            if (scene.getPlayer().getBounds().contains(scene.getHandler().getMouseManager().getMouseX(), scene.getHandler().getMouseManager().getMouseY())) {
                scene.getPlayer().setHearts(scene.getPlayer().getHearts() - 2);
                scene.getLevelManager().getLevel().addAnimation(new BleedEffect(scene.getPlayer(), 2));
            }
        }
    }

    @Override
    public void render(Graphics g) {
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

    private void handleMovement() {
        KeyManager keyManager = scene.getHandler().getKeyManager();
        List<Entity> entityList = scene.getLevelManager().getLevel().getMap().getEntityList();
        if (keyManager.up) {
            checkBounds(0, speed, entityList);
        }
        if (keyManager.down) {
            checkBounds(0, -speed, entityList);
        }
        if (keyManager.left) {
            checkBounds(speed, 0, entityList);
        }
        if (keyManager.right) {
            checkBounds(-speed, 0, entityList);
        }
    }

    private void checkBounds(float xO, float yO, List<Entity> entityList) {
        for (Entity e : entityList) {
            int newX = e.getX() + (int) xO;
            int newY = e.getY() + (int) yO;
            if (e.isSolid() && ((x >= newX && x <= newX + e.getWidth() && y >= newY && y <= newY + e.getHeight())
                            || (x + width >= newX && x + width <= newX + e.getWidth() && y + height >= newY && y + height <= newY + e.getHeight())
                            || (x >= newX && x <= newX + e.getWidth() && y + height >= newY && y + height <= newY + e.getHeight())
                            || (x + width >= newX && x + width <= newX + e.getWidth() && y >= newY && y <= newY + e.getHeight()))) {
                return;
            }
        }
        scene.getCamera().addXOffset((int) xO);
        scene.getCamera().addYOffset((int) yO);
    }

    public float getHearts() {
        return hearts;
    }

    public void setHearts(float hearts) {
        this.hearts = hearts;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public int getCentreX() {
        return x + width / 2;
    }

    public int getCentreY() {
        return y + height / 2;
    }
}

package me.ilucah.hunter.entity.implementation;

import me.ilucah.hunter.entity.Entity;
import me.ilucah.hunter.entity.Interactable;
import me.ilucah.hunter.entity.Permeable;
import me.ilucah.hunter.entity.implementation.moving.Player;
import me.ilucah.hunter.fx.implementation.ExplosionEffect;
import me.ilucah.hunter.input.KeyInteract;
import me.ilucah.hunter.input.MouseInteract;
import me.ilucah.hunter.scene.implementation.gamescene.GameScene;

import java.awt.*;

public class Brick extends Entity implements Permeable, Interactable {

    private final GameScene scene;

    public Brick(GameScene scene, int x, int y, int size) {
        super(x, y, size, 2, true);

        this.scene = scene;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(156, 36, 33));
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
        scene.getLevelManager().getLevel().getMap().getEntityList().remove(this);
        scene.getLevelManager().getLevel().getMap().getEntityList().add(new Dirt(scene, x, y, width));
    }
}

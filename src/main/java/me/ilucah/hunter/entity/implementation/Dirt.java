package me.ilucah.hunter.entity.implementation;

import me.ilucah.hunter.entity.Interactable;
import me.ilucah.hunter.entity.Permeable;
import me.ilucah.hunter.entity.Entity;
import me.ilucah.hunter.entity.implementation.moving.Player;
import me.ilucah.hunter.fx.implementation.BleedEffect;
import me.ilucah.hunter.fx.implementation.LineSweepEffect;
import me.ilucah.hunter.fx.implementation.OverlayFlickerEffect;
import me.ilucah.hunter.input.KeyInteract;
import me.ilucah.hunter.input.MouseInteract;
import me.ilucah.hunter.scene.implementation.gamescene.GameScene;

import java.awt.*;

public class Dirt extends Entity implements Permeable, Interactable {

    private final GameScene scene;

    public Dirt(GameScene scene, int x, int y, int size) {
        super(x, y, size, 0, false);

        this.scene = scene;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(100, 50, 10));
        g.fillRect(x, y, width, height);
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

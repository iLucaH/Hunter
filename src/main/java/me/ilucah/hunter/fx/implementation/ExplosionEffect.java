package me.ilucah.hunter.fx.implementation;

import me.ilucah.hunter.fx.AnimEffect;
import me.ilucah.hunter.scene.implementation.gamescene.GameScene;

import java.awt.*;

public class ExplosionEffect extends AnimEffect {

    private int x, y;

    private final GameScene scene;

    private float radius;

    public ExplosionEffect(GameScene scene, int x, int y) {
        super(1, 60);

        this.scene = scene;
        this.x = x;
        this.y = y;

        this.radius = animEndFrame;
    }

    @Override
    protected void run(Graphics g) {
        x += scene.getCamera().getCachedXOffset();
        y += scene.getCamera().getCachedYOffset();
        radius = Math.min(radius - frameSpeed, 0);
        g.setColor(new Color(180, 20, 20, (int) (200 * (1 - (frame / (float) animEndFrame)))));

        for (int i = 0; i < 365; i++) {
            g.fillOval(x + (int) (radius * Math.cos(i * Math.PI / 180)), y + (int) (radius * Math.sin(i * Math.PI / 180)), 2, 2);
        }
    }
}

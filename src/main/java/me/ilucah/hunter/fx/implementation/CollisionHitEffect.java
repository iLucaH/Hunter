package me.ilucah.hunter.fx.implementation;

import me.ilucah.hunter.fx.AnimEffect;
import me.ilucah.hunter.scene.implementation.gamescene.GameScene;

import java.awt.*;

public class CollisionHitEffect extends AnimEffect {

    private final GameScene scene;

    private int hitX, hitY;

    private final int diameter = 4;

    public CollisionHitEffect(GameScene scene, int hitX, int hitY) {
        super(1, 50);
        this.scene = scene;

        this.hitX = hitX;
        this.hitY = hitY;
    }

    @Override
    protected void run(Graphics g) {
        hitX += scene.getCamera().getCachedXOffset();
        hitY += scene.getCamera().getCachedYOffset();

        g.setColor(new Color(255, 255, 255, 100 - frame * 2));
        g.drawLine(hitX - diameter / 2, hitY + diameter / 2, hitX + diameter / 2, hitY - diameter / 2);
        g.drawLine(hitX - diameter / 2, hitY - diameter / 2, hitX + diameter / 2, hitY + diameter / 2);
    }
}

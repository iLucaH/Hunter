package me.ilucah.hunter.fx.implementation;

import me.ilucah.hunter.entity.Entity;
import me.ilucah.hunter.entity.Interactable;
import me.ilucah.hunter.entity.implementation.Brick;
import me.ilucah.hunter.entity.implementation.moving.Player;
import me.ilucah.hunter.fx.AnimEffect;
import me.ilucah.hunter.scene.implementation.gamescene.GameScene;

import java.awt.*;
import java.util.Optional;

public class LineSweepEffect extends AnimEffect {

    private final int playerX, playerY;
    private int mouseX, mouseY;

    private final GameScene scene;
    private Entity e = null;
    private final Player player;

    public LineSweepEffect(GameScene scene, Player player) {
        super(10, 1000, 1);
        this.scene = scene;
        this.player = player;

        this.mouseX = scene.getHandler().getMouseManager().getMouseX();
        this.mouseY = scene.getHandler().getMouseManager().getMouseY();
        this.playerX = player.getCentreX();
        this.playerY = player.getCentreY();

        getPoints(mouseX, mouseY, playerX, playerY, 100);
        scene.getLevelManager().getLevel().addAnimation(new CollisionHitEffect(scene, mouseX, mouseY));
    }

    @Override
    protected void run(Graphics g) {
        mouseX += scene.getCamera().getCachedXOffset();
        mouseY += scene.getCamera().getCachedYOffset();
        float playerX = mouseX;
        float playerY = mouseY;
        float targetX = this.playerX;
        float targetY = this.playerY;

        float dx = targetX - playerX;
        float dy = targetY - playerY;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance > 0) {
            dx /= distance;
            dy /= distance;

            distance -= frame;
            if (distance <= 0) {
                if (e != null && e.getId() == 2) {
                    ((Interactable) e).onPlayerInteract(player, mouseX, mouseY);
                }
                stopAnimation();
                return;
            }
            float newEndX = playerX + dx * distance;
            float newEndY = playerY + dy * distance;

            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.setColor(new Color(0, 255, 0, 90));
            g.drawLine((int) playerX, (int) playerY, (int) newEndX, (int) newEndY);
            ((Graphics2D) g).setStroke(new BasicStroke(1));
        }
    }

    public int[][] getPoints(int x1, int y1, int x2, int y2, int rayPoints) {
        int[][] points = new int[rayPoints][2];
        int yDiff = y2 - y1, xDiff = x2 - x1;
        double slope = (double) (y2 - y1) / (x2 - x1);
        float x, y;

        --rayPoints;

        for (float i = 0; i < rayPoints; i++) {
            y = slope == 0 ? 0 : (yDiff * (i / rayPoints));
            x = slope == 0 ? xDiff * (i / rayPoints) : (int) (y / slope);
            x = Math.round(x) + x1;
            y = Math.round(y) + y1;
            points[(int) i][0] = Math.round(x) + x1;
            points[(int) i][1] = Math.round(y) + y1;

            Optional<Entity> entity = scene.getLevelManager().getLevel().getMap().getEntityAt((int) x, (int) y);
            if (entity.isPresent() && entity.get().isSolid()) {
                e = entity.get();
                this.mouseX = (int) x;
                this.mouseY = (int) y;
            }
        }
        return points;
    }

    @Override
    public void stopAnimation() {
        frame = animEndFrame;
        if (e != null && e instanceof Brick)
            scene.getLevelManager().getLevel().addAnimation(new ExplosionEffect(scene, mouseX, mouseY));
    }
}

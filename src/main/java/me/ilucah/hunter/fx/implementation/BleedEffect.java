package me.ilucah.hunter.fx.implementation;

import me.ilucah.hunter.entity.implementation.moving.Player;
import me.ilucah.hunter.fx.AnimEffect;

import java.awt.*;

public class BleedEffect extends AnimEffect {

    private final Player player;
    private final float damage;

    private final Color color;
    private final float radius;

    public BleedEffect(Player player, float damage) {
        super(1, 20);
        this.player = player;
        this.damage = damage;

        int color = (int) (player.getHearts() * 10);
        this.color = new Color(color > 255 ? 255 : Math.max(color, 0), 0, 0, 50);
        this.radius = damage * 20;
    }

    @Override
    protected void run(Graphics g) {
        g.setColor(color);
        for (int i = 0; i < 365; i++) {
            g.fillOval(player.getCentreX() + (int) (radius * Math.cos(i*Math.PI/180)), player.getCentreY() + (int) (radius * Math.sin(i*Math.PI/180)), (int) damage * 2, (int) damage * 2);
            g.fillOval(player.getCentreX() + (int) (radius * 2 * Math.cos(i*Math.PI/180)), player.getCentreY() + (int) (radius * 2 * Math.sin(i*Math.PI/180)), (int) damage * 2, (int) damage * 2);
        }
        g.setColor(Color.BLUE);
        g.drawRect(player.getCentreX(), player.getCentreY(), 1, 1);
    }
}

package me.ilucah.hunter.fx.implementation;

import me.ilucah.hunter.fx.AnimEffect;

import java.awt.*;
import java.util.Random;

public class OverlayFlickerEffect extends AnimEffect {

    public static OverlayFlickerEffect existsEffect = null;

    private final Random random = new Random();

    private final int width, height, squares;
    private final int widthSize, heightSize;

    private final LineSweepEffect lineSweepEffect;

    public OverlayFlickerEffect(int width, int height, LineSweepEffect lineSweepEffect) {
        super(1, 180);
        this.lineSweepEffect = lineSweepEffect;

        existsEffect = this;

        this.width = width;
        this.height = height;
        this.squares = 8;
        this.widthSize = width / squares;
        this.heightSize = height / squares;
    }

    @Override
    protected void run(Graphics g) {
        if (lineSweepEffect.isFinished())
            stopAnimation();
        for (int x = 0; x <= squares; x++) {
            for (int y = 0; y <= squares; y++) {
                g.setColor(new Color(0, 0, 0, random.nextInt(80) + 20));
                g.fillRect(x * widthSize, y * heightSize, widthSize, heightSize);
            }
        }
        //g.setColor(new Color(0, 0, 0, random.nextInt(50) + 20));
        //g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        if (existsEffect != this)
            return;
        for (int i = 0; i < 20; i++) {
            g.fillRect(random.nextInt(width), random.nextInt(height), 2, 2);
        }
    }

    @Override
    public void stopAnimation() {
        frame = animEndFrame;
        existsEffect = null;
    }
}

package me.ilucah.hunter.fx;

import java.awt.*;

public abstract class AnimEffect {

    protected int frame = 0;
    protected int frameSpeed, animEndFrame;

    private int priority;

    public AnimEffect(int frameSpeed, int animEndFrame) {
        this(frameSpeed, animEndFrame, 0);
    }

    public AnimEffect(int frameSpeed, int animEndFrame, int priority) {
        this.frameSpeed = frameSpeed;
        this.animEndFrame = animEndFrame;
        this.priority = priority;
    }

    public void render(Graphics g) {
        frame += frameSpeed;
        run(g);
    }

    public boolean isFinished() {
        return frame >= animEndFrame;
    }

    public void stopAnimation() {
        frame = animEndFrame;
    }

    public boolean lastFrame() {
        return frame + frameSpeed >= animEndFrame;
    }

    protected abstract void run(Graphics g);

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}

package me.ilucah.hunter.scene.implementation.gamescene.level;

import me.ilucah.hunter.fx.AnimEffect;
import me.ilucah.hunter.handler.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Level {

    protected Handler handler;
    protected final int width, height;
    protected boolean isRendering;

    protected final Map map;
    private List<AnimEffect> animEffectList = new ArrayList<>();
    private List<AnimEffect> concurrentAnimEffectList = new ArrayList<>();

    public Level(Handler handler, String name, Map map) {
        this.handler = handler;
        this.width = handler.getWidth();
        this.height = handler.getHeight();

        this.map = map;
    }

    public abstract void tick();

    public abstract void renderLevel(Graphics g);

    public void render(Graphics g) {
        isRendering = true;
        renderLevel(g);

        animEffectList = animEffectList.stream()
                .peek(anim -> anim.render(g))
                .filter(anim -> !anim.isFinished())
                .sorted(Comparator.comparingInt(AnimEffect::getPriority))
                .collect(Collectors.toList());
        animEffectList.addAll(concurrentAnimEffectList);
        concurrentAnimEffectList.clear();
        isRendering = false;
    }

    public Map getMap() {
        return map;
    }

    public void addAnimation(AnimEffect animEffect) {
        if (isRendering) {
            concurrentAnimEffectList.add(animEffect);
        } else {
            animEffectList.add(animEffect);
        }
    }

    public List<AnimEffect> getAnimEffectList() {
        return animEffectList;
    }
    public List<AnimEffect> getConcurrentAnimEffectList() {
        return concurrentAnimEffectList;
    }
}

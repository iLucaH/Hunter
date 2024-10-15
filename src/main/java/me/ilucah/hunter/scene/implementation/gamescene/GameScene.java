package me.ilucah.hunter.scene.implementation.gamescene;

import me.ilucah.hunter.camera.GameCamera;
import me.ilucah.hunter.entity.implementation.moving.Player;
import me.ilucah.hunter.handler.Handler;
import me.ilucah.hunter.scene.Scene;
import me.ilucah.hunter.scene.implementation.gamescene.level.LevelManager;

import java.awt.*;

public class GameScene extends Scene {

    private final GameCamera camera;

    private final LevelManager levelManager;

    private final Player player;

    public GameScene(Handler handler) {
        super(handler);

        this.player = new Player(this, handler.getWidth() / 2, handler.getHeight() / 2);
        camera = new GameCamera(handler);
        levelManager = new LevelManager(this);
    }

    @Override
    public void tick() {
        levelManager.getLevel().tick();
        camera.tick(levelManager.getLevel().getMap().getEntityList());
    }

    @Override
    public void render(Graphics g) {
        levelManager.getLevel().render(g);
    }

    public Handler getHandler() {
        return handler;
    }

    public Player getPlayer() {
        return player;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public GameCamera getCamera() {
        return camera;
    }
}
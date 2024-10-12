package me.ilucah.hunter.scene.implementation.gamescene;

import me.ilucah.hunter.camera.GameCamera;
import me.ilucah.hunter.handler.Handler;
import me.ilucah.hunter.scene.Scene;

import java.awt.*;

public class GameScene extends Scene {

    private final GameCamera camera;

    public GameScene(Handler handler) {
        super(handler);
        camera = new GameCamera(handler);
    }

    @Override
    public void tick() {
        camera.tick();
    }

    @Override
    public void render(Graphics g) {
    }
}
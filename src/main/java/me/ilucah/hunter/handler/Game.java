package me.ilucah.hunter.handler;

import me.ilucah.hunter.display.Display;
import me.ilucah.hunter.display.render.RenderFactory;
import me.ilucah.hunter.input.KeyManager;
import me.ilucah.hunter.input.MouseManager;
import me.ilucah.hunter.scene.Scene;
import me.ilucah.hunter.scene.implementation.gamescene.GameScene;
import me.ilucah.hunter.scene.implementation.mainmenu.MainMenuScene;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    public static Game getGame() {
        return game;
    }

    private static Game game;

    private Display display;
    private final int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    // Scenes
    private GameScene gameScene;
    private MainMenuScene mainMenuScene;

    // Input
    private final KeyManager keyManager;
    private final MouseManager mouseManager;

    private BufferStrategy bs;
    private Graphics g;

    // Handler
    private Handler handler;

    public Game(String title, int width, int height) {
        game = this;
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void initialise() {
        display = new Display(title, width, height);
        display.getCanvas().addKeyListener(keyManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        handler = new Handler(this);


        gameScene = new GameScene(handler);
        mainMenuScene = new MainMenuScene(handler);

        Scene.setScene(mainMenuScene);
    }

    private void tick() {
        keyManager.tick();

        if (Scene.getScene() != null) {
            RenderFactory.getThreadPool().submit(() -> Scene.getScene().tick());
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (Scene.getScene() != null) {
            RenderFactory.getThreadPool().submit(() -> Scene.getScene().render(g));
            RenderFactory.getThreadPool().submit(() -> RenderFactory.getRenders().forEachOrdered(ro -> ro.render().accept(g)));
            RenderFactory.clear();
        }

        bs.show();
        g.dispose();
    }

    @SuppressWarnings("unused")
    @Override
    public void run() {
        initialise();

        int fps = 60;
        double timePerTick = (double) 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public Handler getHandler() {
        return handler;
    }

    public Display getDisplay() {
        return display;
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
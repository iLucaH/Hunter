package me.ilucah.hunter.scene.implementation.mainmenu;

import me.ilucah.hunter.handler.Handler;
import me.ilucah.hunter.scene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenuScene extends Scene {

    public MainMenuScene(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
            Scene.setScene(handler.getGame().getGameScene());
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(50, 0, 139));
        g.fillRect(0,0, width, height);
        g.setColor(Color.GREEN);
        g.drawLine(0,0,width,height);
    }
}

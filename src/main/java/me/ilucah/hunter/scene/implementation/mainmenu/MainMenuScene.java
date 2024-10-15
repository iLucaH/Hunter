package me.ilucah.hunter.scene.implementation.mainmenu;

import me.ilucah.hunter.handler.Handler;
import me.ilucah.hunter.interactables.Button;
import me.ilucah.hunter.scene.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuScene extends Scene {

    private final List<Button> buttons = new ArrayList<>();

    public MainMenuScene(Handler handler) {
        super(handler);
        buttons.add(new Button(width / 2, height / 2, 150) {
            @Override
            public void onClick() {
                Scene.setScene(handler.getGame().getGameScene());
                handler.getMouseManager().setLeftPressed(false);
            }
        });
    }

    @Override
    public void tick() {
        buttons.forEach(button -> button.tick(handler.getMouseManager()));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,width,height);

        // button
        buttons.forEach(button -> button.render(g, new int[]{255,0,0}, "PLAY"));

        // text
        drawTitle(g);
    }

    private void drawTitle(Graphics g) {
        g.drawString("Hunter 2D", width / 2 - 40, 150);
    }
}

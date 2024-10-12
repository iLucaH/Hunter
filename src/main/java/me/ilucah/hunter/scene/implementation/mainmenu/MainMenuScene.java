package me.ilucah.hunter.scene.implementation.mainmenu;

import me.ilucah.hunter.handler.Handler;
import me.ilucah.hunter.scene.Scene;

import java.awt.*;

public class MainMenuScene extends Scene {

    public MainMenuScene(Handler handler) {
        super(handler);
        System.out.println(width + " g" + height);
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize().width + " " + Toolkit.getDefaultToolkit().getScreenSize().height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(50, 0, 139));
        g.fillRect(0,0, width, height);
        g.setColor(Color.GREEN);
        g.drawLine(0,0,width,height);
    }
}

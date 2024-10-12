package me.ilucah.hunter.scene;

import me.ilucah.hunter.handler.Handler;

import java.awt.*;

public abstract class Scene {

    private static Scene currentState = null;

    public static void setScene(Scene state){
        currentState = state;
    }

    public static Scene getScene(){
        return currentState;
    }

    //CLASS

    protected Handler handler;
    protected final int width, height;

    public Scene(Handler handler) {
        this.handler = handler;
        this.width = handler.getHeight();
        this.height = handler.getHeight();;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}

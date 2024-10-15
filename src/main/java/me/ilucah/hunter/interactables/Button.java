package me.ilucah.hunter.interactables;

import me.ilucah.hunter.input.MouseManager;

import java.awt.*;

public abstract class Button {

    private final int x, y, size;
    private final Rectangle bounds;

    public Button(int x, int y, int size) {
        this.x = x - size / 2;
        this.y = y - size / 2;
        this.size = size;

        bounds = new Rectangle(x, y, size, size);
    }

    public void render(Graphics g, int[] color, String text) {
        Color c = new Color(color[0], color[1], color[2]);
        g.setColor(c);
        g.fillRect(x, y, size, size);

        g.setColor(Color.black);
        g.drawRect(x, y, size, size);

        FontMetrics metrics = g.getFontMetrics(new Font("Arial", Font.BOLD, 14));
        int xx = x + (size - metrics.stringWidth(text)) / 2;
        int yy = y + ((size - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(text, xx, yy);
    }

    public void tick(MouseManager mouseManager) {
        if (!mouseManager.isLeftPressed())
            return;
        if (mouseManager.getMouseX() >= x && mouseManager.getMouseX() <= x + size && mouseManager.getMouseY() >= y && mouseManager.getMouseY() <= y + size) {
            onClick();
        }
    }

    public abstract void onClick();

}

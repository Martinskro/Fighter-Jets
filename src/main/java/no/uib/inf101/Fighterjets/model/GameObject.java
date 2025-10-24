package no.uib.inf101.Fighterjets.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected double x, y;
    protected BufferedImage image;

    public GameObject(double x, double y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public abstract void update();

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

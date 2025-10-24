package no.uib.inf101.Fighterjets.model;

import java.awt.Rectangle;

public class BulletModel {
    private double x;
    private double y;
    private double velocity;
    private double angle;
    private boolean active = true;

    public BulletModel(double x, double y, double angle, double velocity) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.velocity = velocity;
    }

    public void update() {
        x += velocity * Math.sin(angle);
        y -= velocity * Math.cos(angle);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean intersects(Object hitbox) {
        if (hitbox instanceof Rectangle) {
            Rectangle rect = (Rectangle) hitbox;
            return x >= rect.x && x < rect.x + rect.width && y >= rect.y && y < rect.y + rect.height;
        }
        return false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

}

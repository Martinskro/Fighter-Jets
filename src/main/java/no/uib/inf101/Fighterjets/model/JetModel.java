package no.uib.inf101.Fighterjets.model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

public class JetModel {
    private double x;
    private double y;
    private double angle;
    private int screenWidth;
    private int screenHeight;
    private int playerSpeed;
    private List<BulletModel> bullets;
    private BufferedImage jetImage;
    private static final long bulletCooldown = 200;
    private long lastShootTime = 0;
    private static GameState gameState;

    /*
     * Constructor for JetModel
     */
    public JetModel(int x, int y, int screenWidth, int screenHeight, int playerSpeed, BufferedImage jetImage) {
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.playerSpeed = playerSpeed;
        this.jetImage = jetImage;
        JetModel.gameState = GameState.WELCOME_SCREEN;
        bullets = new ArrayList<>();
    }

    /*
     * Update the jet's position and angle
     */
    public void update(boolean rotateLeft, boolean rotateRight, boolean shooting) {
        if (rotateLeft) {
            angle -= Math.toRadians(playerSpeed-1);
        }
        if (rotateRight) {
            angle += Math.toRadians(playerSpeed-1);
        }
        if (shooting) {
            shoot();
        }

        x += playerSpeed * Math.sin(angle);
        y -= playerSpeed * Math.cos(angle);

        // Screen wrapping
        if (x < -22) {
            x = screenWidth;
        } else if (x > screenWidth) {
            x = -22;
        }
        if (y < -16) {
            y = screenHeight;
        } else if (y > screenHeight) {
            y = -16;
        }
    }

    /*
     * Create a new bullet and add it to the list of bullets
     */
    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime > bulletCooldown) {
            double bulletSpeed = 8;
            BulletModel bullet = new BulletModel(getNoseX(), getNoseY(), angle, bulletSpeed);
            bullets.add(bullet);
            lastShootTime = currentTime;
        }
    }

    public void updateBullets() {
        Iterator<BulletModel> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            BulletModel bullet = iterator.next();
            bullet.update();
            if (!bullet.isActive()) {
                iterator.remove(); // Remove inactive bullets
            }
        }
    }

    /*
     * Getters and setters
     */
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    private double getNoseX() {
        return x + jetImage.getWidth() / 2;
    }

    private double getNoseY() {
        return y + jetImage.getHeight() / 2;
    }

    public List<BulletModel> getBullets() {
        return bullets;
    }

    public Object getHitbox() {
        return new Rectangle((int) x, (int) y, jetImage.getWidth(), jetImage.getHeight());
    }
}

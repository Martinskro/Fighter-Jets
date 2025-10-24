package no.uib.inf101.sample;

import org.junit.jupiter.api.Test;

import no.uib.inf101.Fighterjets.model.BulletModel;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;

public class BulletModelTest {

    @Test
    public void testBulletModelCreation() {
        double x = 100.0;
        double y = 200.0;
        double angle = Math.PI / 4;
        double velocity = 10.0;

        BulletModel bulletModel = new BulletModel(x, y, angle, velocity);

        assertNotNull(bulletModel);
        assertEquals(x, bulletModel.getX());
        assertEquals(y, bulletModel.getY());
        assertTrue(bulletModel.isActive());
    }

    @Test
    public void testBulletModelUpdate() {
        double x = 100.0;
        double y = 200.0;
        double angle = Math.PI / 4;
        double velocity = 10.0;

        BulletModel bulletModel = new BulletModel(x, y, angle, velocity);

        bulletModel.update();

        double expectedX = x + velocity * Math.sin(angle);
        double expectedY = y - velocity * Math.cos(angle);

        assertEquals(expectedX, bulletModel.getX());
        assertEquals(expectedY, bulletModel.getY());
    }

    @Test
    public void testBulletModelIntersects() {
        double x = 100.0;
        double y = 200.0;
        double angle = Math.PI / 4;
        double velocity = 10.0;

        BulletModel bulletModel = new BulletModel(x, y, angle, velocity);

        // Test intersection with a rectangle
        assertTrue(bulletModel.intersects(new Rectangle(90, 190, 20, 20)));

        // Test non-intersection with a rectangle
        assertFalse(bulletModel.intersects(new Rectangle(0, 0, 10, 10)));
    }

    @Test
    public void testBulletModelSetActive() {
        double x = 100.0;
        double y = 200.0;
        double angle = Math.PI / 4;
        double velocity = 10.0;

        BulletModel bulletModel = new BulletModel(x, y, angle, velocity);

        bulletModel.setActive(false);

        assertFalse(bulletModel.isActive());
    }

}
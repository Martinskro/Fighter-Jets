package no.uib.inf101.sample;
import org.junit.jupiter.api.Test;

import no.uib.inf101.Fighterjets.model.JetModel;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.image.BufferedImage;

public class JetModelTest {

    // ...

    @Test
    public void testJetModelCreation() {
        // ...

            int x = 100;
            int y = 200;
            int screenWidth = 800;
            int screenHeight = 600;
            int playerSpeed = 5;
            BufferedImage jetImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            JetModel jetModel = new JetModel(x, y, screenWidth, screenHeight, playerSpeed, jetImage);

            assertNotNull(jetModel);
            assertEquals(x, jetModel.getX());
            assertEquals(y, jetModel.getY());
            assertEquals(0.0, jetModel.getAngle());
            assertNotNull(jetModel.getBullets());
            assertTrue(jetModel.getBullets().isEmpty());
    }

    @Test
    public void testJetModelUpdate() {
        int x = 100;
        int y = 200;
        int screenWidth = 800;
        int screenHeight = 600;
        int playerSpeed = 0;
        BufferedImage jetImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

        JetModel jetModel = new JetModel(x, y, screenWidth, screenHeight, playerSpeed, jetImage);

        // Test update without any input
        jetModel.update(false, false, false);
        assertEquals(x, jetModel.getX());
        assertEquals(y, jetModel.getY());
        assertEquals(0.0, jetModel.getAngle());

        // Test update with rotation
        jetModel.update(true, false, false);
        assertEquals(x, jetModel.getX());
        assertEquals(y, jetModel.getY());
        assertEquals(-Math.toRadians(playerSpeed - 1), jetModel.getAngle());

        // Test update with shooting
        jetModel.update(false, false, true);
        assertEquals(1, jetModel.getBullets().size());
    }

}
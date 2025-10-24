package no.uib.inf101.sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.Fighterjets.view.GamePanel;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GamePanelTest {

    private GamePanel gamePanel;

    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
    }

    @Test
    public void testGamePanelCreation() {
        assertNotNull(gamePanel);
        assertEquals(JPanel.class, gamePanel.getClass().getSuperclass());
        assertEquals(Runnable.class, gamePanel.getClass().getInterfaces()[0]);
    }

    @Test
    public void testGamePanelDimensions() {
        Dimension expectedDimension = new Dimension(800, 600);
        assertEquals(expectedDimension, gamePanel.getPreferredSize());
    }

    @Test
    public void testGamePanelBackground() {
        Color expectedColor = Color.GRAY;
        assertEquals(expectedColor, gamePanel.getBackground());
    }

    @Test
    public void testGamePanelScore() {
        assertEquals(0, gamePanel.getScoreWhiteJet());
        assertEquals(0, gamePanel.getScoreBlackJet());
    }

}
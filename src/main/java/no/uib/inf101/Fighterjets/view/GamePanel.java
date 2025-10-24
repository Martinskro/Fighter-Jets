package no.uib.inf101.Fighterjets.view;

import javax.swing.JPanel;

import no.uib.inf101.Fighterjets.controller.FighterJetController;
import no.uib.inf101.Fighterjets.model.BulletModel;
import no.uib.inf101.Fighterjets.model.JetModel;
import no.uib.inf101.Fighterjets.model.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int screenWidth = 800;
    final int screenHeight = 600;

    // FPS
    int FPS = 60;

    FighterJetController controller = new FighterJetController();
    Thread gameThread;

    JetModel whiteJet;
    JetModel blackJet;

    BufferedImage whiteJetImage;
    BufferedImage blackJetImage;
    BufferedImage whiteBulletImage;
    BufferedImage blackBulletImage;
    BufferedImage welcomeImage;
    BufferedImage gameOverImage;

    private GameState gameState = GameState.WELCOME_SCREEN;

    // Score
    private int scoreWhiteJet = 0;
    private int scoreBlackJet = 0;

    public GamePanel() {
        gameState = GameState.WELCOME_SCREEN;
        try {
            // Load images
            whiteJetImage = ImageIO.read(new File("src/main/java/no/uib/inf101/Fighterjets/resources/white-jet.png"));
            blackJetImage = ImageIO.read(new File("src/main/java/no/uib/inf101/Fighterjets/resources/black-jet.png"));
            whiteBulletImage = ImageIO
                    .read(new File("src/main/java/no/uib/inf101/Fighterjets/resources/white-bullet.png"));
            blackBulletImage = ImageIO
                    .read(new File("src/main/java/no/uib/inf101/Fighterjets/resources/black-bullet.png"));
            welcomeImage = ImageIO
                    .read(new File("src/main/java/no/uib/inf101/Fighterjets/resources/welcome-screen.png"));
            gameOverImage = ImageIO
                    .read(new File("src/main/java/no/uib/inf101/Fighterjets/resources/gameover-screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        whiteJet = new JetModel(150, 100, screenWidth, screenHeight, 4, whiteJetImage);
        blackJet = new JetModel(600, 100, screenWidth, screenHeight, 4, blackJetImage);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(controller);
        this.setFocusable(true);
    }

    /*
     * Start the game thread
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double updateTime = 1000000000.0 / FPS;
        double delta = 0;

        while (gameThread != null) {
            long now = System.nanoTime();
            delta += (now - lastTime) / updateTime;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            // DRAW: draws screen with updated info
            repaint();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (gameState == GameState.WELCOME_SCREEN && controller.spacePressed) {
            gameState = GameState.ACTIVE_GAME;
            // controller.spacePressed = false; // Reset the flag
        }
        if (gameState == GameState.ACTIVE_GAME) {
            // Game update logic for movement and bullet handling
            whiteJet.update(controller.aPressed, controller.dPressed, controller.wPressed);
            blackJet.update(controller.leftPressed, controller.rightPressed, controller.upPressed);

            whiteJet.updateBullets();
            blackJet.updateBullets();

            // Collision detection and score updating
            for (BulletModel bullet : whiteJet.getBullets()) {
                if (bullet.intersects(blackJet.getHitbox())) {
                    bullet.setActive(false);
                    scoreWhiteJet++;
                }
            }

            for (BulletModel bullet : blackJet.getBullets()) {
                if (bullet.intersects(whiteJet.getHitbox())) {
                    bullet.setActive(false);
                    scoreBlackJet++;
                }
            }

            // Check for game over condition
            if (scoreWhiteJet >= 10 || scoreBlackJet >= 10) {
                gameState = GameState.GAME_OVER;
            }
        }
    }

    /*
     * Paint the game screen
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState == GameState.WELCOME_SCREEN) {
            g2.drawImage(welcomeImage, 0, 0, this);
        } else {
            // Draw white jet
            if (whiteJetImage != null) {
                AffineTransform transformWhite = new AffineTransform();
                transformWhite.translate(whiteJet.getX() + whiteJetImage.getWidth() / 2,
                        whiteJet.getY() + whiteJetImage.getHeight() / 2);
                transformWhite.rotate(whiteJet.getAngle());
                transformWhite.translate(-whiteJetImage.getWidth() / 2, -whiteJetImage.getHeight() / 2);
                g2.drawImage(whiteJetImage, transformWhite, null);
            }

            // Draw black jet
            if (blackJetImage != null) {
                AffineTransform transformBlack = new AffineTransform();
                transformBlack.translate(blackJet.getX() + blackJetImage.getWidth() / 2,
                        blackJet.getY() + blackJetImage.getHeight() / 2);
                transformBlack.rotate(blackJet.getAngle());
                transformBlack.translate(-blackJetImage.getWidth() / 2, -blackJetImage.getHeight() / 2);
                g2.drawImage(blackJetImage, transformBlack, null);
            }

            for (BulletModel bullet : whiteJet.getBullets()) {
                g2.drawImage(blackBulletImage, (int) bullet.getX(), (int) bullet.getY(), null);
            }

            for (BulletModel bullet : blackJet.getBullets()) {
                g2.drawImage(whiteBulletImage, (int) bullet.getX(), (int) bullet.getY(), null);
            }

            // Draw scores
            try {
                // Load the font file
                File fontFile = new File("src/main/java/no/uib/inf101/Fighterjets/resources/PressStart2P-Regular.ttf");
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                Font sizedFont = font.deriveFont(Font.BOLD, 30);

                g2.setFont(sizedFont);
                g2.setColor(Color.WHITE);
                g2.drawString(String.valueOf(scoreWhiteJet), 50, 70);
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(scoreBlackJet), 730, 70);

            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

        }
        if (gameState == GameState.GAME_OVER) {
            g2.drawImage(gameOverImage, 0, 0, this);
            g2.setFont(font());
            g2.setColor(Color.WHITE);

            if (scoreWhiteJet >= 10) {
                Inf101Graphics.drawCenteredString(g2, "White Jet Wins!", (screenWidth / 2), (screenHeight / 4));
                Inf101Graphics.drawCenteredString(g2, "Press 'R' to restart.", (screenWidth / 2), (screenHeight / 4) + 100);
                if (controller.rPressed) {
                    scoreWhiteJet = 0;
                    scoreBlackJet = 0;
                    whiteJet = new JetModel(150, 100, screenWidth, screenHeight, 4, whiteJetImage);
                    blackJet = new JetModel(600, 100, screenWidth, screenHeight, 4, blackJetImage);
                    gameState = GameState.ACTIVE_GAME;
                }
            } else {
                Inf101Graphics.drawCenteredString(g2, "Black Jet Wins!", (screenWidth / 2), (screenHeight / 4));
                Inf101Graphics.drawCenteredString(g2, "Press 'R' to restart.", (screenWidth / 2), (screenHeight / 4) + 100);
                if (controller.rPressed) {
                    scoreWhiteJet = 0;
                    scoreBlackJet = 0;
                    whiteJet = new JetModel(150, 100, screenWidth, screenHeight, 4, whiteJetImage);
                    blackJet = new JetModel(600, 100, screenWidth, screenHeight, 4, blackJetImage);
                    gameState = GameState.ACTIVE_GAME;
                }
            }
        }
        g2.dispose();
    }

    public Integer getScoreWhiteJet() {
        return scoreWhiteJet;
    }

    public Integer getScoreBlackJet() {
        return scoreBlackJet;
    }

    private Font font() {
        try {
            // Load the font file
            File fontFile = new File("src/main/java/no/uib/inf101/Fighterjets/resources/PressStart2P-Regular.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font sizedFont = font.deriveFont(Font.BOLD, 30);
            return sizedFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}

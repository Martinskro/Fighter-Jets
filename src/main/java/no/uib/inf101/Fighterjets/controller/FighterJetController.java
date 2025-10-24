package no.uib.inf101.Fighterjets.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FighterJetController implements KeyListener {

    public boolean aPressed, dPressed, leftPressed, rightPressed, wPressed, upPressed, spacePressed, rPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /*
     * Set the boolean values to true when the corresponding key is pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A) {
            aPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            dPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_W) {
            wPressed = true;
        }
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_R) {
            rPressed = true;
        }
    }

    /*
     * Set the boolean values to false when the corresponding key is released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A) {
            aPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            dPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_W) {
            wPressed = false;
        }
        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_R) {
            rPressed = false;
        }
    }
}

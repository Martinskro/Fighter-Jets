package no.uib.inf101;

import javax.swing.JFrame;

import no.uib.inf101.Fighterjets.view.GamePanel;

public class Main {
  private static final String windowTitle = "Maskr1070 FIGHTERJETS";

  public static void main(String[] args) {
    startGame();
  }

  public static void startGame(){
    JFrame frame = new JFrame(windowTitle);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    GamePanel gamePanel = new GamePanel();
    frame.add(gamePanel);

    frame.pack();

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    gamePanel.startGameThread();
  }

}
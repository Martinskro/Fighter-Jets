// package no.uib.inf101.Fighterjets.view;

// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.Image;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;

// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;

// public class WelcomeScreen {

//     BufferedImage welcomeImage;

//     public WelcomeScreen(){
//     }

//     public void drawWelcomeScreen(Graphics g) {
//         try {
//             // Load images
//             welcomeImage = ImageIO.read(new File("src/main/java/no/uib/inf101/Fighterjets/resources/welcome-screen.png"));
//         } catch(IOException e) {
//             e.printStackTrace();
//         }
        
//         Graphics2D g2 = (Graphics2D) g;

//         g2.drawImage(welcomeImage, 0, 0, this);
//     }
        
// }

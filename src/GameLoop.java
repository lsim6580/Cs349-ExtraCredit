import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Game loop example.
 * Note, this program needs some refactoring.
 */
public class GameLoop extends JFrame {
    // Constants

    public static final Color SPRITE_COLOR = Color.RED;

    // User-controlled object

    private boolean moved = false;

    private GameCanvas canvas; // the custom drawing canvas (extends JPanel)

    /** Constructor to set up the GUI */
    public GameLoop() {

        // Set up a custom drawing JPanel
        canvas = new GameCanvas();
        canvas.setPreferredSize(new Dimension(400, 140));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);

        // JFrame responds to left and right arrow KeyEvent
        addKeyListener(canvas.getGameLogic());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the close button
        setTitle("Game Loop Example");
        requestFocus();

    }


    /** The entry main() method */
    public static void main(String[] args) {
        GameLoop game = new GameLoop();
        game.pack();
        game.setVisible(true);
        game.start();
    }

    private void start() {
        canvas.gameLoop();
    }
}
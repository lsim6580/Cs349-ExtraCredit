import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by luke on 12/11/2016.
 */
public class GameCanvas extends JPanel{
    public static final Color CANVAS_BACKGROUND = Color.CYAN;
    private boolean gameRunning = true;
    private static final int ONE_BILLION = 1000000000;
    private static final int ONE_MILLION = 1000000;
    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 140;
    private SuperAwesomeDude superAwesomeDude;
    private SuperAwesomeDude badAwesomeDude;
    private GameLogic gameLogic;
    boolean moved;
    public GameCanvas(){
        this.superAwesomeDude = new SuperAwesomeDude((CANVAS_WIDTH /4),(CANVAS_HEIGHT /4), (Color.GREEN),30,30);
        this.badAwesomeDude = new SuperAwesomeDude(CANVAS_WIDTH /4 + 40, CANVAS_HEIGHT /4 + 40, Color.RED,30,30);
       gameLogic = new GameLogic(superAwesomeDude, badAwesomeDude);

       // set the focus to JFrame to receive KeyEvent
    }
    public void gameLoop() {
        long nanosecondAccumulator = 0;
        long fps = 0;

        // nano = billion
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 20;
        // optimal nanoseconds each frame
        final long OPTIMAL_NANOSECONDS_EACH_FRAME = ONE_BILLION / TARGET_FPS;

        // If you plan to update gameRunning
        //   on a separate thread consider declaring
        //   gameRunning as volatile.
        while (gameRunning) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_NANOSECONDS_EACH_FRAME);

            // The following isn't necessary. Can be commented out.
            // Only used to calculate fps
            nanosecondAccumulator = nanosecondAccumulator +  updateLength;
            fps = fps + 1;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (nanosecondAccumulator >= ONE_BILLION) {
                System.out.println("(FPS: " + fps + ")");
                nanosecondAccumulator = 0;
                fps = 0;
            }


            // update the game logic
            gameLogic.doGameUpdates();

            // draw frame
            render();

            try {
                long nanosecondsThisFrame = System.nanoTime() - lastLoopTime;
                long nanosecondsToSleepToGetDesiredFrameRate =
                        OPTIMAL_NANOSECONDS_EACH_FRAME - nanosecondsThisFrame;
                // divide by 1M to get milliseconds to sleep
                Thread.sleep(nanosecondsToSleepToGetDesiredFrameRate / ONE_MILLION);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    public GameLogic getGameLogic(){
        return gameLogic;
    }

    private void render() {
        repaint();
    }





    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(CANVAS_BACKGROUND);
        g.setColor(superAwesomeDude.getColor());
        g.fillOval(superAwesomeDude.getX(), superAwesomeDude.getY(), superAwesomeDude.getWidth(), superAwesomeDude.getHeight());

        g.setColor(badAwesomeDude.getColor());
        g.fillOval(badAwesomeDude.getX(), badAwesomeDude.getY(), badAwesomeDude.getWidth(), badAwesomeDude.getHeight());

    }
}

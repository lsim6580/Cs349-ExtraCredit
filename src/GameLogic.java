import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by luke on 12/13/2016.
 */
public class GameLogic implements KeyListener{
    private boolean hasMoved = false;
    SuperAwesomeDude superAwesomeDude;
    SuperAwesomeDude badAwesomeDude;

    @Override
    public void keyReleased(KeyEvent evt){

    }

    @Override
    public void keyTyped(KeyEvent evt){

    }
    @Override
    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                superAwesomeDude.setX(superAwesomeDude.getX() - 4);
                break;
            case KeyEvent.VK_RIGHT:
                superAwesomeDude.setX(superAwesomeDude.getX()+ 4);
                break;
            case KeyEvent.VK_UP:
                superAwesomeDude.setY(superAwesomeDude.getY() - 4);
                break;
            case  KeyEvent.VK_DOWN:
                superAwesomeDude.setY(superAwesomeDude.getY() + 4);
                break;
        }
        hasMoved = true;
    }
    GameLogic(SuperAwesomeDude superAwesomeDude, SuperAwesomeDude badAwesomeDude){
        this.superAwesomeDude = superAwesomeDude;
        this.badAwesomeDude = badAwesomeDude;
    }
    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void doGameUpdates() {
        if(hasMoved) {
            if (badAwesomeDude.getX() > superAwesomeDude.getX()) {
                badAwesomeDude.setX((badAwesomeDude.getX()) - 2);
            } else {
                badAwesomeDude.setX((badAwesomeDude.getX()) + 2);
            }
            if (badAwesomeDude.getY() > superAwesomeDude.getY()) {
                badAwesomeDude.setY((badAwesomeDude.getY()) - 2);
            } else {
                badAwesomeDude.setY((badAwesomeDude.getY()) + 2);
            }
            if(Math.abs(superAwesomeDude.getX()-badAwesomeDude.getX()) < 10  && Math.abs(superAwesomeDude.getY()-badAwesomeDude.getY()) < 10){
                JOptionPane.showMessageDialog(null, "You Lose!");
                System.exit(0);
            }
        }


    }
}

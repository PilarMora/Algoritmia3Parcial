package dijkstra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author pi
 */
public class Key implements KeyListener {
    Panel dp;

    public Key(Panel dp) {
        this.dp = dp;
    }


    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();

        if(key == KeyEvent.VK_ENTER) {
            dp.autoSearch();
        }
        if(key == KeyEvent.VK_1) {
            dp.search();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}

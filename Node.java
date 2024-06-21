package dijkstra;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author pi
 */
public class Node extends JButton implements ActionListener {
    Node parent;
    int column;
    int row;
    int costToGoal;
    int distance;
    int totalCost;
    boolean start;
    boolean wall;
    boolean goal;
    boolean visited;
    boolean available;

    public Node(int column, int row) {
        this.column = column;
        this.row = row;

        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }

    public void setAsStart() {
        setBackground(Color.magenta);
        setForeground(Color.white);
        setText("I");
        start = true;
    }

    public void setAsGoal() {
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("F");
        goal = true;
    }

    public void setAsWall() {
        setBackground(Color.PINK);
        setForeground(Color.BLUE);
        wall = true;
    }

    public void setAsAvailable() {
        available = true;
    }

    public void setAsVisited() {
        if(start == false && goal == false) {
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.black);
        }
        visited = true;
    }

    public void setAsPath() {
        setBackground(Color.green);
        setForeground(Color.black);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
    }

}

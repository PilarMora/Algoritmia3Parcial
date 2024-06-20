package dijkstra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author pi
 */
public class Panel extends JPanel {
    int maxColumn = 25;
    int maxRow = 20;
    int nodeSize = 50;
    int screenWidth = nodeSize * maxColumn;
    int screenHeight = nodeSize * maxRow;

    // NODE
    Node[][] nodes = new Node[maxColumn][maxRow];
    Node startNode, goalNode, currentNode;

    ArrayList<Node> availableNodes = new ArrayList<>();
    ArrayList<Node> visitedNodes = new ArrayList<>();

    boolean goalReached = false;
    int step = 0;

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.pink);
        this.setLayout(new GridLayout(maxRow, maxColumn));
        this.addKeyListener(new Key(this));
        this.setFocusable(true);

        int column = 0;
        int row = 0;

        while(column < maxColumn && row < maxRow) {
            nodes[column][row] = new Node(column, row);
            this.add(nodes[column][row]);

            column++;
            if(column == maxColumn) {
                column = 0;
                row++;
            }
        }

				nodes[4][13].setAsStart();
				startNode = nodes[4][13];
				currentNode = startNode;

				nodes[10][12].setAsGoal();
				goalNode = nodes[10][12];

        setSolidNode(13, 7);
        setSolidNode(12, 7);
        setSolidNode(11, 7);
        setSolidNode(10, 7);
        setSolidNode(9, 7);
        setSolidNode(9, 8);
        setSolidNode(9, 9);
        setSolidNode(9, 10);
        setSolidNode(9, 11);
        setSolidNode(9, 12);
        setSolidNode(9, 13);
        setSolidNode(10, 13);
        setSolidNode(11, 13);
        setSolidNode(12, 13);
        setSolidNode(13, 13);
        setSolidNode(14, 13);
        setSolidNode(15, 13);
        setSolidNode(15, 14);
        setSolidNode(15, 15);

        // SET COST
        setCostOnNodes();
    }

    private void setSolidNode(int column, int row) {
        nodes[column][row].setAsWall();
    }

    private void setCostOnNodes() {
        int column = 0;
        int row = 0;

        while(column < maxColumn && row < maxRow) {
            getCost(nodes[column][row]);
            column++;
            if(column == maxColumn) {
                column = 0;
                row++;
            }
        }
    }

    /**
     *
     * G cost:
     *      The distance between the current nodes and the start nodes.
     *
     * H cost:
     *      The distance from the current nodes to the goal nodes.
     *
     * F cost:
     *      The total cost (G + H) of the nodes.
     *
     */

    private void getCost(Node nodes) {
        // GET G COST (The distance from the start nodes)
        int xDistance = Math.abs(nodes.column - startNode.column);
        int yDistance = Math.abs(nodes.row - startNode.row);
        nodes.costToGoal = xDistance + yDistance;

        // GET H COST (The distance from the goal nodes)
        xDistance = Math.abs(nodes.column - goalNode.column);
        yDistance = Math.abs(nodes.row - goalNode.row);
        nodes.distance = xDistance + yDistance;

        // GET F COST (The total cost)
        nodes.totalCost = nodes.costToGoal + nodes.distance;

        /* if(nodes != startNode && nodes != goalNode) {
            nodes.setText("<html>F: " + nodes.fCost + "<br>G: " + nodes.gCost + "</html>");
        } */
    }

    public void search() {
        if(goalReached == false && step < 300) {
            int column = currentNode.column;
            int row = currentNode.row;

            currentNode.setAsVisited();
            visitedNodes.add(currentNode);
            availableNodes.remove(currentNode);

            // OPEN THE UP NODE
            if(row - 1 >= 0)
                openNode(nodes[column][row - 1]);
            // OPEN THE LEFT NODE
            if(column - 1 >= 0)
                openNode(nodes[column - 1][row]);
            // OPEN THE DOWN NODE
            if(row + 1 < maxRow)
                openNode(nodes[column][row + 1]);
            // OPEN THE RIGHT NODE
            if(column + 1 < maxColumn)
                openNode(nodes[column + 1][row]);

            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < availableNodes.size(); i++) {
                // Check if this nodes's F cost is better
                if(availableNodes.get(i).totalCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = availableNodes.get(i).totalCost;
                } else if(availableNodes.get(i).totalCost == bestNodefCost) {
                    // If F cost is equal, check the G cost
                    if(availableNodes.get(i).costToGoal < availableNodes.get(bestNodeIndex).costToGoal) {
                        bestNodeIndex = i;
                    }
                }
            }
            // After the loop, we get the best nodes which is our next step
            currentNode = availableNodes.get(bestNodeIndex);

            if(currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }

    public void autoSearch() {
        while(goalReached == false && step < 300) {
            int column = currentNode.column;
            int row = currentNode.row;

            currentNode.setAsVisited();
            visitedNodes.add(currentNode);
            availableNodes.remove(currentNode);

            // OPEN THE UP NODE
            if(row - 1 >= 0)
                openNode(nodes[column][row - 1]);
            // OPEN THE LEFT NODE
            if(column - 1 >= 0)
                openNode(nodes[column - 1][row]);
            // OPEN THE DOWN NODE
            if(row + 1 < maxRow)
                openNode(nodes[column][row + 1]);
            // OPEN THE RIGHT NODE
            if(column + 1 < maxColumn)
                openNode(nodes[column + 1][row]);

            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < availableNodes.size(); i++) {
                // Check if this nodes's F cost is better
                if(availableNodes.get(i).totalCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = availableNodes.get(i).totalCost;
                } else if(availableNodes.get(i).totalCost == bestNodefCost) {
                    // If F cost is equal, check the G cost
                    if(availableNodes.get(i).costToGoal < availableNodes.get(bestNodeIndex).costToGoal) {
                        bestNodeIndex = i;
                    }
                }
            }
            // After the loop, we get the best nodes which is our next step
            currentNode = availableNodes.get(bestNodeIndex);

            if(currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }

    private void openNode(Node nodes) {
        if(nodes.available == false && nodes.visited == false && nodes.wall == false) {
            //If the nodes is not opened yet, add it to the open list
            nodes.setAsAvailable();
            nodes.parent = currentNode;
            availableNodes.add(nodes);
        }
    }

    private void trackThePath() {
        // Backtrack and draw the best path
        Node current = goalNode;

        while(current != startNode) {
            current = current.parent;
            if(current != startNode) {
                current.setAsPath();
            }
        }
    }

}

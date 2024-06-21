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

    private void getCost(Node nodes) {
        int xDistance = Math.abs(nodes.column - startNode.column);
        int yDistance = Math.abs(nodes.row - startNode.row);
        nodes.costToGoal = xDistance + yDistance;

        xDistance = Math.abs(nodes.column - goalNode.column);
        yDistance = Math.abs(nodes.row - goalNode.row);
        nodes.distance = xDistance + yDistance;

        nodes.totalCost = nodes.costToGoal + nodes.distance;

    }

    public void search() {
        if(goalReached == false && step < 300) {
            int column = currentNode.column;
            int row = currentNode.row;

            currentNode.setAsVisited();
            visitedNodes.add(currentNode);
            availableNodes.remove(currentNode);

            if(column + 1 < maxColumn)
                openNode(nodes[column + 1][row]);

            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < availableNodes.size(); i++) {
                if(availableNodes.get(i).totalCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = availableNodes.get(i).totalCost;
                } else if(availableNodes.get(i).totalCost == bestNodefCost) {
                    if(availableNodes.get(i).costToGoal < availableNodes.get(bestNodeIndex).costToGoal) {
                        bestNodeIndex = i;
                    }
                }
            }
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

            if(row - 1 >= 0)
                openNode(nodes[column][row - 1]);
            if(column - 1 >= 0)
                openNode(nodes[column - 1][row]);
            if(row + 1 < maxRow)
                openNode(nodes[column][row + 1]);
            if(column + 1 < maxColumn)
                openNode(nodes[column + 1][row]);

            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < availableNodes.size(); i++) {
                if(availableNodes.get(i).totalCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = availableNodes.get(i).totalCost;
                } else if(availableNodes.get(i).totalCost == bestNodefCost) {
                    if(availableNodes.get(i).costToGoal < availableNodes.get(bestNodeIndex).costToGoal) {
                        bestNodeIndex = i;
                    }
                }
            }
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
            nodes.setAsAvailable();
            nodes.parent = currentNode;
            availableNodes.add(nodes);
        }
    }

    private void trackThePath() {
        Node current = goalNode;

        while(current != startNode) {
            current = current.parent;
            if(current != startNode) {
                current.setAsPath();
            }
        }
    }

}

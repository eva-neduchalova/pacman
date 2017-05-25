package cz.evcino.pacman.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.joml.Vector2i;

import cz.evcino.pacman.ApplicationConstants;

public class ShortestMazePath {

    class Node {

        private final int x;
        private final int y;
        private final int weight;
        private final Node previous;

        Node(int x, int y, Node previous, int weight) {
            this.x = x;
            this.y = y;
            this.previous = previous;
            this.weight = weight;
        }
        public int getX() {
            return this.x;
        }
        public int getY() {
            return this.y;
        }
        public int getWeight() {
            return this.weight;
        }
        public Node getPrevious() {
            return this.previous;
        }
        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            final Node n = (Node)o;
            return ((n.x == x) && (n.y == y));
        }
        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + x;
            result = 31 * result + y;
            return result;
        }
        @Override
        public String toString() {
            return "Current X is " + this.x + " Current Y is " + this.y;
        }
    }

    private boolean isValid(boolean[][] maze, int m, int n, Node node) {
        return (node.getX() >= 0 && node.getX() < m) && (node.getY() >= 0 && node.getY() < n) && maze[node.getX()][node.getY()];

    }

    private Node updatedNode(Node currentNode, Node visitedNode) {
        if (currentNode.weight + 1 < visitedNode.weight) {
            return new Node(visitedNode.getX(), visitedNode.getY(), currentNode, currentNode.weight + 1);
        } else {
            return visitedNode;
        }
    }

    private Set<Node> getNeighbors(Node current, int m, int n, boolean[][] maze) {
        int currentX = current.getX();
        int currentY = current.getY();
        int currentWeight = current.getWeight();
        Set<Node> validNeighbors = new HashSet<Node>();
        List<Node> neighbors = new ArrayList<Node>() {

            {
                add(new Node(currentX - 1, currentY, current, currentWeight + 1));
                add(new Node(currentX + 1, currentY, current, currentWeight + 1));
                add(new Node(currentX, currentY + 1, current, currentWeight + 1));
                add(new Node(currentX, currentY - 1, current, currentWeight + 1));
            }
        };
        for (Node node : neighbors) {
            if (isValid(maze, m, n, node)) {
                validNeighbors.add(node);
            }
        }
        return validNeighbors;
    }

    private static boolean[][] createMaze() {
        // init original maze from string

        int mazeRows = ApplicationConstants.MAZE_DEFINITION_STRING.length;
        int mazeColumns = ApplicationConstants.MAZE_DEFINITION_STRING[0].length();
        boolean[][] maze = new boolean[mazeColumns][mazeRows];

        int x = 0;
        int y = 0;
        for (String row : ApplicationConstants.MAZE_DEFINITION_STRING) {
            x = 0;
            for (char c : row.toCharArray()) {
                if ('X' == c) {
                    maze[x][y] = false;
                } else {
                    maze[x][y] = true;
                }
                x++;
            }
            y++;
        }

        // boolean[][] maze = { { true, true, true }, { true, true, false }, { true, true, false } };
        return maze;
    }

    private void printQueue(Set<Node> q) {
        for (Node n : q) {
            System.out.println("Queue contains node" + n);
        }
    }

    private void printMap(Map<Node, Node> m) {
        for (Map.Entry<Node, Node> entry : m.entrySet()) {
            System.out.println("visited contains node " + entry.getKey());
        }
    }

    public Node findPath(boolean[][] maze, int p, int q, Node start, Node end) {
        Queue<Node> inProcess = new LinkedList<Node>();
        Map<Node, Node> visited = new HashMap<Node, Node>();
        Set<Node> inProcessQueue = new HashSet<Node>();
        inProcess.add(start);
        inProcessQueue.add(start);
        Node current = null;
        while ((current = inProcess.poll()) != null) {
            if (current.equals(end)) {
                return current;
            } else {
                Set<Node> neighbors = getNeighbors(current, p, q, maze);
                for (Node n : neighbors) {
                    if (!inProcessQueue.contains(n)) {
                        if (visited.containsKey(n)) {
                            Node visitedNode = visited.get(n);
                            visited.remove(n);
                            Node updatedNode = updatedNode(current, visitedNode);
                            visited.put(updatedNode, updatedNode);
                        } else {
                            inProcess.add(n);
                            inProcessQueue.add(n);
                        }
                    }
                }
                visited.put(current, current);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        findNextStep(1, 1, 19, 14);
    }


    public static Vector2i findNextStep(int startX, int startY, int endX, int endY) {
        boolean[][] maze = createMaze();
        ShortestMazePath spm = new ShortestMazePath();
        ShortestMazePath.Node startNode = spm.new Node(startX, startY, null, 0);
        ShortestMazePath.Node endNode = spm.new Node(endX, endY, null, 0);
        ShortestMazePath.Node pathNode = spm.findPath(maze, 20, 15, startNode, endNode);
        while (pathNode != null) {
            // System.out.println("Reached pathNode" + pathNode);
            if (pathNode.getWeight() == 1) {
                //System.out.println("Final pathNode:" + pathNode + ". Start pathNode:" + startNode);
                return new Vector2i(pathNode.x, pathNode.y);
            }
            pathNode = pathNode.getPrevious();
        }
        return new Vector2i();
    }
}
package cz.evcino.pacman.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.evcino.pacman.enums.GhostStatus;
import cz.evcino.pacman.enums.MazeLocationStatus;
import cz.evcino.pacman.enums.MovementDirection;
import cz.evcino.pacman.objects.AbstractMovableObject;
import cz.evcino.pacman.objects.Ghost;
import cz.evcino.pacman.objects.Maze;
import cz.evcino.pacman.objects.Pacman;

public class MovementUtils {

    protected static final Logger logger = LoggerFactory.getLogger(MovementUtils.class);

    public static final float DOT_SCALE = 0.25f;
    public static final long DOT_SCORE = 100;
    public static final float SUPERDOT_SCALE = 0.5f;
    public static final long SUPERDOT_SCORE = 200;

    private static final long GHOST_SCORE = 0;


    public static boolean canMove(AbstractMovableObject movable, Maze maze) {

        int currentMazeX = getRelativeXLocationAfterStep(movable, maze, null);
        int currentMazeY = getRelativeYLocationAfterStep(movable, maze, null);

        float currentMazeXAbsolute = convertRelativeXToAbsolute(currentMazeX, maze);
        float currentMazeYAbsolute = convertRelativeYToAbsolute(currentMazeY, maze);

        int newMazeX = getRelativeXLocationAfterStep(movable, maze, movable.getDirection());
        int newMazeY = getRelativeYLocationAfterStep(movable, maze, movable.getDirection());

        MazeLocationStatus mazeStatus = maze.getValue(newMazeX, newMazeY);
        boolean mazeStatusOk = mazeStatus == null || !MazeLocationStatus.WALL.equals(mazeStatus);
        if (!mazeStatusOk) {
            return false;
        }

        float currentMovableX = getAbsoluteXLocationAfterStep(movable, maze, null);
        float currentMovableY = getAbsoluteYLocationAfterStep(movable, maze, null);

        float newMovableX = getAbsoluteXLocationAfterStep(movable, maze, movable.getDirection());
        float newMovableY = getAbsoluteYLocationAfterStep(movable, maze, movable.getDirection());

        double currentDistanceToCenter = Math.hypot(currentMovableX - currentMazeXAbsolute, currentMovableY - currentMazeYAbsolute);
        double newDistanceToCenter = Math.hypot(newMovableX - currentMazeXAbsolute, newMovableY - currentMazeXAbsolute);
        boolean headingToCenterOfCurrentSquare = newDistanceToCenter < currentDistanceToCenter;

        if (headingToCenterOfCurrentSquare) {
            return true;
        }

        // heading towards another square - requires another check

        Vector2f rightSidePosition = new Vector2f(newMovableX + 0.8f, newMovableY);
        Vector2f leftSidePosition = new Vector2f(newMovableX - 0.8f, newMovableY);
        Vector2f topSidePosition = new Vector2f(newMovableX, newMovableY + 0.8f);
        Vector2f bottomSidePosition = new Vector2f(newMovableX, newMovableY - 0.8f);

        Set<Vector2f> positionsToCheck = new HashSet<>();
        if (movable instanceof Pacman) {
            if (movable.getDirection() == MovementDirection.LEFT) {
                positionsToCheck.add(leftSidePosition);
            }
            if (movable.getDirection() == MovementDirection.RIGHT) {
                positionsToCheck.add(rightSidePosition);
            }
            if (movable.getDirection() == MovementDirection.UP) {
                positionsToCheck.add(topSidePosition);
            }
            if (movable.getDirection() == MovementDirection.DOWN) {
                positionsToCheck.add(bottomSidePosition);
            }
        } else {

            if (movable.getDirection() == MovementDirection.LEFT) {
                positionsToCheck.add(leftSidePosition);
            }
            if (movable.getDirection() == MovementDirection.RIGHT) {
                positionsToCheck.add(rightSidePosition);
            }
            if (movable.getDirection() == MovementDirection.UP) {
                positionsToCheck.add(topSidePosition);
            }
            if (movable.getDirection() == MovementDirection.DOWN) {
                positionsToCheck.add(bottomSidePosition);
            }

            // if (movable.getDirection() != MovementDirection.LEFT) {
            // positionsToCheck.add(rightSidePosition);
            // }
            // if (movable.getDirection() != MovementDirection.RIGHT) {
            // positionsToCheck.add(leftSidePosition);
            // }
            // if (movable.getDirection() != MovementDirection.UP) {
            // positionsToCheck.add(bottomSidePosition);
            // }
            // if (movable.getDirection() != MovementDirection.DOWN) {
            // positionsToCheck.add(topSidePosition);
            // }
        }


        for (Vector2f position : positionsToCheck) {
            int mazeX2 = convertAbsoluteToRelativeCoordinateX(position.x, maze);
            int mazeY2 = convertAbsoluteToRelativeCoordinateY(position.y, maze);
            MazeLocationStatus mazeStatus2 = maze.getValue(mazeX2, mazeY2);
            boolean mazeStatusOk2 = mazeStatus2 == null || !MazeLocationStatus.WALL.equals(mazeStatus2);
            if (!mazeStatusOk2) {
                return false;
            }
        }

        return mazeStatusOk;
    }


    public static boolean evaluateDots(Pacman pacman, List<Ghost> ghosts, Maze maze) {

        int newMazeX = getRelativeXLocationAfterStep(pacman, maze, pacman.getDirection());
        int newMazeY = getRelativeYLocationAfterStep(pacman, maze, pacman.getDirection());

        MazeLocationStatus mazeStatus = maze.getValue(newMazeX, newMazeY);

        if (MazeLocationStatus.DOT.equals(mazeStatus) || MazeLocationStatus.POWER_DOT.equals(mazeStatus)) {

            float newPacmanX = getAbsoluteXLocationAfterStep(pacman, maze, pacman.getDirection());
            float newPacmanY = getAbsoluteYLocationAfterStep(pacman, maze, pacman.getDirection());

            float dotPositionX = getAbsoluteXLocation(newMazeX, maze);
            float dotPositionY = getAbsoluteYLocation(newMazeY, maze);


            double distance = Math.hypot(newPacmanX - dotPositionX, newPacmanY - dotPositionY);
            float distanceThreshold = (pacman.getDiameter() + (MazeLocationStatus.DOT.equals(mazeStatus) ? DOT_SCALE : SUPERDOT_SCALE));
            if (distance < distanceThreshold * maze.SQUARE_LENGTH) {
                pacman.setScore(pacman.getScore() + (MazeLocationStatus.DOT.equals(mazeStatus) ? DOT_SCORE : SUPERDOT_SCORE));
                maze.setValue(newMazeX, newMazeY, MazeLocationStatus.EMPTY);
                // TODO - superdot sideeffects
                if (MazeLocationStatus.POWER_DOT.equals(mazeStatus)) {
                    for (Ghost ghost : ghosts) {
                        ghost.setStatus(GhostStatus.BLIND);
                    }
                }

                return true;
            }
        }

        return false;
    }

    public static boolean evaluateGhosts(Pacman pacman, List<Ghost> ghosts, Maze maze) {

        for (Ghost ghost : ghosts) {
            if (Math.hypot(ghost.getLocationX() - pacman.getLocationX(), ghost.getLocationY() - pacman.getLocationY()) < 1f) {
                if (ghost.getStatus().equals(GhostStatus.BLIND)) {
                    ghost.setLocation(ghost.getDefaultLocation());
                    ghost.setStatus(GhostStatus.CHASE);
                    pacman.setScore(pacman.getScore() + (GHOST_SCORE));
                } else {
                    pacman.setExtraLives(pacman.getExtraLives() - 1);
                    pacman.setLocation(pacman.getDefaultLocation());
                    for (Ghost ghost2 : ghosts) {
                        ghost2.setLocation(ghost2.getDefaultLocation());
                        ghost2.setStatus(GhostStatus.CHASE);
                    }
                }
                return true;
            }
        }

        return false;
    }

    public static boolean canGhostChangeDirection(Ghost ghost, Maze maze) {
        boolean canMoveInCurrentDirection = canMove(ghost, maze);
        boolean isAtCrossRoad = isAtCrossRoad(ghost, maze);
        // if (isAtCrossRoad) {
        // MovementUtils.fixGhostPosition(ghost, maze);
        // }
        return !canMoveInCurrentDirection || isAtCrossRoad;
    }

    public static void selectGhostDirection(Ghost ghost, Pacman pacman, Maze maze) {
        List<MovementDirection> availableDirections = new ArrayList<>();
        MovementDirection[] directionsToCheck = MovementDirection.values();

        for (MovementDirection dir : directionsToCheck) {
            ghost.setDirection(dir);
            if (canMove(ghost, maze)) {
                availableDirections.add(dir);
            }
        }

        if (availableDirections.isEmpty()) {
            ghost.setDirection(null);
            return;
        }

        if (availableDirections.size() == 1) {
            ghost.setDirection(availableDirections.get(0));
            return;
        }

        if (GhostStatus.CHASE.equals(ghost.getStatus())) {
            int pacmanRelativeX = getRelativeXLocationAfterStep(pacman, maze, null);
            int pacmanRelativeY = getRelativeYLocationAfterStep(pacman, maze, null);

            int ghostRelativeX = getRelativeXLocationAfterStep(ghost, maze, null);
            int ghostRelativeY = getRelativeYLocationAfterStep(ghost, maze, null);


            Vector2i nextStep = ShortestMazePath.findNextStep(ghostRelativeX, ghostRelativeY, pacmanRelativeX, pacmanRelativeY);

            MovementDirection selectedDirection = null;

            if (nextStep.x > ghostRelativeX) {
                selectedDirection = MovementDirection.RIGHT;
            } else if (nextStep.x < ghostRelativeX) {
                selectedDirection = MovementDirection.LEFT;
            } else if (nextStep.y > ghostRelativeY) {
                selectedDirection = MovementDirection.UP;
            } else if (nextStep.y < ghostRelativeY) {
                selectedDirection = MovementDirection.DOWN;
            } else {
                // random
                int index = new Random().nextInt(availableDirections.size() - 1);
                ghost.setDirection(availableDirections.get(index));
            }


            ghost.setDirection(selectedDirection);

        } else if (GhostStatus.BLIND.equals(ghost.getStatus())) {
            // run away from pacman

            float pacmanRelativeX = getRelativeXLocationAfterStep(pacman, maze, null);
            float pacmanRelativeY = getRelativeYLocationAfterStep(pacman, maze, null);

            MovementDirection selectedDirection = null;
            double maxDistance = 0;
            // find shortest path to pacman TODO
            for (MovementDirection dir : availableDirections) {
                int newMazeX = getRelativeXLocationAfterStep(ghost, maze, dir);
                int newMazeY = getRelativeYLocationAfterStep(ghost, maze, dir);
                double distance = Math.hypot(pacmanRelativeX - newMazeX, pacmanRelativeY - newMazeY);
                if (distance > maxDistance) {
                    maxDistance = distance;
                    selectedDirection = dir;
                }
            }

            ghost.setDirection(selectedDirection);

        } else {
            // random
            int index = new Random().nextInt(availableDirections.size() - 1);
            ghost.setDirection(availableDirections.get(index));
        }


    }

    private static boolean isAtCrossRoad(Ghost ghost, Maze maze) {
        MovementDirection direction = ghost.getDirection();
        if (direction == null) {
            return true;
        }

        float ghostAbsoluteX = getAbsoluteXLocationAfterStep(ghost, maze, null);
        float ghostAbsoluteY = getAbsoluteYLocationAfterStep(ghost, maze, null);

        int ghostMazeX = getRelativeXLocationAfterStep(ghost, maze, null);
        int ghostMazeY = getRelativeYLocationAfterStep(ghost, maze, null);

        float mazeAbsolutX = convertRelativeXToAbsolute(ghostMazeX, maze);
        float mazeAbsolutY = convertRelativeYToAbsolute(ghostMazeY, maze);

        if (Math.hypot(ghostAbsoluteX - mazeAbsolutX, ghostAbsoluteY - mazeAbsolutY) < (Maze.SQUARE_LENGTH * 0.3f)) {
            // ok
        } else {
            return false;
        }

        // if ((Math.abs(ghostAbsoluteX) - Math.floor(Math.abs(ghostAbsoluteX))) < TOLERANCE && Math.abs(ghostAbsoluteY) -
        // (Math.floor(Math.abs(ghostAbsoluteY))) < TOLERANCE) {
        // // ok
        // } else {
        // return false;
        // }

        if (MovementDirection.UP.equals(direction) || MovementDirection.DOWN.equals(direction)) {
            // check left and right
            int mazeIndexXLeft = convertAbsoluteToRelativeCoordinateX(ghostAbsoluteX - Maze.SQUARE_LENGTH, maze);
            int mazeIndexXRight = convertAbsoluteToRelativeCoordinateX(ghostAbsoluteX + Maze.SQUARE_LENGTH, maze);
            int mazeIndexY = convertAbsoluteToRelativeCoordinateY(ghostAbsoluteY, maze);

            MazeLocationStatus mazeStatus = maze.getValue(mazeIndexXLeft, mazeIndexY);
            if (mazeStatus == null || !MazeLocationStatus.WALL.equals(mazeStatus)) {
                return true;
            }

            mazeStatus = maze.getValue(mazeIndexXRight, mazeIndexY);
            if (mazeStatus == null || !MazeLocationStatus.WALL.equals(mazeStatus)) {
                return true;
            }

        } else {
            // check up and down
            int mazeIndexX = convertAbsoluteToRelativeCoordinateX(ghostAbsoluteX, maze);
            int mazeIndexYUp = convertAbsoluteToRelativeCoordinateX(ghostAbsoluteY + Maze.SQUARE_LENGTH, maze);
            int mazeIndexYDown = convertAbsoluteToRelativeCoordinateY(ghostAbsoluteY - Maze.SQUARE_LENGTH, maze);

            MazeLocationStatus mazeStatus = maze.getValue(mazeIndexX, mazeIndexYUp);
            if (mazeStatus == null || !MazeLocationStatus.WALL.equals(mazeStatus)) {
                return true;
            }

            mazeStatus = maze.getValue(mazeIndexX, mazeIndexYDown);
            if (mazeStatus == null || !MazeLocationStatus.WALL.equals(mazeStatus)) {
                return true;
            }

        }

        return false;
    }


    private static float getAbsoluteXLocationAfterStep(AbstractMovableObject movable, Maze maze, MovementDirection direction) {
        float locationX = movable.getLocationX();
        if (MovementDirection.LEFT.equals(direction)) {
            locationX -= movable.getSpeed();
        } else if (MovementDirection.RIGHT.equals(direction)) {
            locationX += movable.getSpeed();
        }
        return locationX;
    }

    private static float getAbsoluteYLocationAfterStep(AbstractMovableObject movable, Maze maze, MovementDirection direction) {
        float locationY = movable.getLocationY();
        if (MovementDirection.DOWN.equals(direction)) {
            locationY -= movable.getSpeed();
        } else if (MovementDirection.UP.equals(direction)) {
            locationY += movable.getSpeed();
        }
        return locationY;
    }


    private static int convertAbsoluteToRelativeCoordinateX(float locationX, Maze maze) {
        float mazeX = locationX / Maze.SQUARE_LENGTH + (float)maze.getColumns() / 2;
        if (maze.getColumns() % 2 != 0) {
            mazeX += -0.5f;
        }
        return Math.round(mazeX);
    }

    private static int convertAbsoluteToRelativeCoordinateY(float locationY, Maze maze) {
        float mazeY = locationY / Maze.SQUARE_LENGTH + (float)maze.getRows() / 2;
        if (maze.getRows() % 2 != 0) {
            mazeY += -0.5f;
        }
        return Math.round(mazeY);
    }


    private static float convertRelativeXToAbsolute(int mazeX, Maze maze) {
        return (mazeX - (float)maze.getColumns() / 2) * Maze.SQUARE_LENGTH;
    }

    private static float convertRelativeYToAbsolute(int mazeY, Maze maze) {
        return (mazeY - (float)maze.getRows() / 2) * Maze.SQUARE_LENGTH;
    }


    private static int getRelativeXLocationAfterStep(AbstractMovableObject movable, Maze maze, MovementDirection movementDirection) {
        float locationX = getAbsoluteXLocationAfterStep(movable, maze, movementDirection);
        float mazeX = (locationX / Maze.SQUARE_LENGTH + (float)maze.getColumns() / 2);
        if (maze.getColumns() % 2 != 0) {
            mazeX += -0.5f;
        }
        return Math.round(mazeX);
    }

    private static int getRelativeYLocationAfterStep(AbstractMovableObject movable, Maze maze, MovementDirection movementDirection) {
        float locationY = getAbsoluteYLocationAfterStep(movable, maze, movementDirection);
        float mazeY = (locationY / Maze.SQUARE_LENGTH + (float)maze.getRows() / 2);
        if (maze.getRows() % 2 != 0) {
            mazeY += -0.5f;
        }
        return Math.round(mazeY);
    }

    public static float getAbsoluteYLocation(int indexY, Maze maze) {
        return (indexY - (float)maze.getRows() / 2) * Maze.SQUARE_LENGTH;
    }

    public static float getAbsoluteXLocation(int indexX, Maze maze) {
        return (indexX - (float)maze.getColumns() / 2) * Maze.SQUARE_LENGTH;
    }

    public static void fixGhostPosition(Ghost ghost, Maze maze) {

        float ghostAbsoluteX = getAbsoluteXLocationAfterStep(ghost, maze, null);
        float ghostAbsoluteY = getAbsoluteYLocationAfterStep(ghost, maze, null);

        int ghostMazeX = getRelativeXLocationAfterStep(ghost, maze, null);
        int ghostMazeY = getRelativeYLocationAfterStep(ghost, maze, null);

        float mazeAbsolutX = convertRelativeXToAbsolute(ghostMazeX, maze);
        float mazeAbsolutY = convertRelativeYToAbsolute(ghostMazeY, maze);


        if (Math.hypot(ghostAbsoluteX - mazeAbsolutX, ghostAbsoluteY - mazeAbsolutY) < (Maze.SQUARE_LENGTH / 2)) {
            // fix position
            ghost.setLocationX(mazeAbsolutX);
            ghost.setLocationY(mazeAbsolutY);
        }
    }

}

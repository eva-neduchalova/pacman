package cz.evcino.pacman.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.evcino.pacman.objects.Maze;
import cz.evcino.pacman.objects.MazeLocationStatus;
import cz.evcino.pacman.objects.Pacman;

public class MovementUtils {

    protected static final Logger logger = LoggerFactory.getLogger(MovementUtils.class);

    public static boolean canMove(Pacman pacman, Maze maze) {

        logger.info(pacman.toLogString());
        
        float mazeX = (pacman.getLocationX() / Maze.SQUARE_LENGTH + (float)maze.getColumns() / 2);
        float mazeY = (pacman.getLocationY() / Maze.SQUARE_LENGTH + (float)maze.getRows() / 2);

        if (maze.getColumns() % 2 != 0) {
            mazeX += -0.5f;
        }
        if (maze.getRows() % 2 != 0) {
            mazeY += -0.5f;
        }


        int newMazeX = 0;
        int newMazeY = 0;

        switch (pacman.getDirection()) {
        case DOWN:
            newMazeX = Math.round(mazeX);
            newMazeY = (int)Math.round(Math.floor(mazeY));
            break;
        case LEFT:
            newMazeX = (int)Math.round(Math.floor(mazeX));
            newMazeY = Math.round(mazeY);
            break;
        case RIGHT:
            newMazeX = (int)Math.round(Math.ceil(mazeX));
            newMazeY = Math.round(mazeY);
            break;
        case UP:
            newMazeX = Math.round(mazeX);
            newMazeY = (int)Math.round(Math.ceil(mazeY));
            break;
        default:
            break;
        }

        MazeLocationStatus mazeStatus = maze.getValue(newMazeX, newMazeY);
        boolean result = mazeStatus != null && !MazeLocationStatus.WALL.equals(mazeStatus);

//        logger.info(pacman.toLogString());
//        logger.info(String.format("pacman at location [%f, %f], moving towards [%d, %d]. allowed=%s", mazeX, mazeY, newMazeX, newMazeY,
//                result));

        return result;
    }


}

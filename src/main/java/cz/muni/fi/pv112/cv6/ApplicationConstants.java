package cz.muni.fi.pv112.cv6;


public class ApplicationConstants {

    public static final String WINDOW_TITLE = "Evčin Pacman";

    public static final int DEFAULT_WINDOW_WIDTH = 1280;
    public static final int DEFAULT_WINDOW_HEIGHT = 720;

    public static final String PATH_TO_SHADERS = "src/main/resources/shaders/";
    public static final String PATH_TO_TEXTURES = "src/main/resources/textures/";
    public static final String PATH_TO_MODELS = "src/main/resources/models/";

    // the maze difinition string
    public static final String[] MAZE_DEFINITION_STRING = { // 21 columns
            "XXXXXXXXXXXXXXXXXXXXX", // 1
            "X.........X.........X", // 2
            "XOXXX.XXX.X.XXX.XXXOX", // 3
            "X......X..X.........X", // 4
            "XXX.XX.X.XXX.XX.X.X.X", // 5
            "X....X..........X.X.X", // 6
            "X.XX.X.XXX-XXX.XX.X.X", // 7
            "X.XX.X.X     X......X", // 8
            "X.XX...XGG GGX.XXXX.X", // 9
            "X.XX.X.XXXXXXX.XXXX.X", // 10
            "X....X....P.........X", // 11
            "XXX.XX.XXXXXXX.X.X.XX", // 12
            "X.........X....X....X", // 13
            "XOXXXXXXX.X.XXXXXXXOX", // 14
            "X...................X", // 15
            "XXXXXXXXXXXXXXXXXXXXX", // 16
    };
    
    public static final int MAZE_ROWS = MAZE_DEFINITION_STRING.length;
    public static final int MAZE_COLUMNS = MAZE_DEFINITION_STRING[0].length();

}

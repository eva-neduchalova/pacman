package cz.evcino.pacman.enums;

public enum MazeLocationStatus {
    WALL("X"),
    EMPTY(" "),
    DOT("."),
    POWER_DOT("O");

    private String shortcut;

    private MazeLocationStatus(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut() {
        return shortcut;
    }

}
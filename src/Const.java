package src;

public final class Const {
    public static final int CANVAS_WIDTH = 512;
    public static final int CANVAS_HEIGHT = 512;
    public static final int SCREEN_WIDTH = 2 * CANVAS_WIDTH;
    public static final int SCREEN_HEIGHT = CANVAS_HEIGHT;
    public static final int NUM_RAYS = 512;

    public static final int MS_PER_S = 1000;
    public static final int FRAME_RATE = MS_PER_S / 45;
    public static final int UPDATE_RATE = FRAME_RATE;

    public static final String MAP_FILE_NAME = "assets/map/map3.txt";
    public static final int TILE_WIDTH = 32;

    public static final double PLAYER_MOVE_SPEED = 3;
    public static final double PLAYER_TURN_SPEED = 5;
    public static final double PLAYER_VIEW_WIDTH = 80;
    public static final double PLAYER_VIEW_HEIGHT = 50;
    public static final double PLAYER_HEIGHT = 5;

    private Const() {}
}

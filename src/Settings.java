/**
 * Created by tyler on 1/29/17.
 */
public interface Settings {

    int WINDOW_WIDTH = 700;
    int WINDOW_HEIGHT = 550;

    int BRICK_COLS = 6;
    int BRICK_ROWS = 4;         // n x 10 Bricks per row
    int BRICK_STRENGTH = 1;     // Brick strength
    int BRICK_TOP_PADDING = 20;
    int BRICK_SIDE_PADDING = 12;
    int BRICK_HEIGHT = 15;

    int PLAYER_HEIGHT = 10;

    int PLAYER_INC_SPEED = 2;

    int BALL_DIAMETER = 15;

    int GAME_LOAD_TIME = 400;
    int GAME_UPDATE_INTERVAL = 5;


    /*
    Values below are automatically calculated
     */

    int GAME_BOTTOM = WINDOW_HEIGHT - 70;

    // Calculated to keep bricks evenly sized and centered based on padding and # of cols
    int BRICK_WIDTH = (WINDOW_WIDTH - 2 * (BRICK_SIDE_PADDING + BRICK_COLS-1)) / BRICK_COLS;

    int PLAYER_WIDTH = (int)(BRICK_WIDTH * .75);

    int PLAYER_POS_X = WINDOW_WIDTH/2 - PLAYER_WIDTH/2;
    int PLAYER_POS_Y = GAME_BOTTOM - PLAYER_HEIGHT;

    int BALL_POS_X = PLAYER_POS_X + PLAYER_WIDTH/2;
    int BALL_POS_Y = PLAYER_POS_Y - BALL_DIAMETER - 1;


}

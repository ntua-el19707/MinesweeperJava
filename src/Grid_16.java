import javafx.scene.layout.GridPane;

/**
 * class Grid_16
 * 
 * @author Victoras Giannki
 */
public class Grid_16 extends Grid {
    /**
     * constructor Grid_16 (16x16 grid)
     * 
     * @param lis   gchange
     * @param lis2  SuperBombListener
     * @param bombs int
     */
    Grid_16(gchange lis, SuperBombListener lis2, int bombs) {
        dimensions = 16;
        play_buttons = new playbutton[dimensions][dimensions];
        draw_init();
        listener = lis;
        listener2 = lis2;
        board = new int[dimensions][dimensions];
        // size=500;
        ScreenWidth = 510;
        ScreenHeight = 700;
        totalbombs = bombs;

    }

}
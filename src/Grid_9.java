/**
 * class Grid_9
 * 
 * @author Victoras Giannki
 */
public class Grid_9 extends Grid {
  /**
   * constructor Grid_9 (9x9 grid)
   * 
   * @param lis   gchange
   * @param lis2  SuperBombListener
   * @param bombs int
   */
  Grid_9(gchange lis, SuperBombListener lis2, int bombs) {
    dimensions = 9;
    play_buttons = new playbutton[dimensions][dimensions];
    draw_init();
    listener = lis;
    listener2 = lis2;
    board = new int[dimensions][dimensions];
    // size =300;
    ScreenHeight = 500;
    ScreenWidth = 300;

    totalbombs = bombs;

  }
}
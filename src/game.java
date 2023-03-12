package com.vic;

import com.vic.senario;
import com.vic.senario_9;
import com.vic.senario_16;

/**
 * Game class
 * 
 * @author Victoras Giannaki
 */
public class game {

    private int startx;
    private int starty;
    private boolean start = false;

    private enum state {
        win,
        pending,
        lose
    }

    private state game_state = state.pending;
    private int not_bombs;
    private int count = 0;
    private int game_mode;
    private senario game_senario;
    private int[][] game_board;
    private int game_rows;
    private boolean[][] hidden;

    /**
     * Class game constructor specifying starting play(first move ).
     * 
     * @param x ;
     * @param y ;
     *          player first move is (x,y)
     */
    public game(int x, int y) {
        game_mode = 2;
        // System.out.printf("Create Game object \n");
        startx = x;
        starty = y;
        // System.out.println(x+" "+y);
    }

    /**
     * method get_game_finished()
     * 
     * @return game_state if is pennding(false) or finished (true)
     */
    public boolean get_game_finished() {
        if (game_state != state.pending) {
            return true;
        }
        return false;

    }

    private void initialize_a_game() {
        game_board = game_senario.board();
        game_rows = game_senario.get_Rows();
        hidden = new boolean[game_rows][game_rows];

        not_bombs = game_senario.get_not_bombs();

        for (int i = 0; i < game_rows; i++) {
            for (int j = 0; j < game_rows; j++) {
                hidden[i][j] = false; // default value
            }
        }
        // game_senario.print();

    }

    /**
     * method new game Create a new game
     * 
     * @param type       int 0(9x9) 1(16x16)
     * @param bombs      int number of bombs
     * @param time       int number of time
     * @param superBombs int 0 no Super Bomb 1 yes Super Bomb
     */
    public void new_game(int type, int bombs, int time, int superBombs) {
        game_mode = type;

        // System.out.print(startx +" "+starty);
        switch (game_mode) {
            case 0:

                game_senario = new senario_9(bombs, startx, starty);
                // System.out.println("ok");
                initialize_a_game();
                start = true;
                break;
            case 1:
                game_senario = new senario_16(bombs, startx, starty);
                game_senario.set(startx, starty);
                game_senario.pr();
                initialize_a_game();
                start = true;

                break;
            default:
                System.out.printf("Wrong input  of game mode\n");
                break;
        }
    }

    /**
     * method getgame()
     * 
     * @return get game Board
     */
    public int[][] getgame() {
        return game_board;
    }

    private void reveal(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (x + i < 0 || y + j < 0 || x + i >= game_rows || y + j >= game_rows) {
                } else if (!hidden[x + i][y + j]) {

                    hidden[x + i][y + j] = true;
                    ++count;
                    if (game_board[x + i][y + j] == 0) {
                        reveal(x + i, y + j);
                    }

                }
            }

        }

    }

    /**
     * method msg_state()
     * 
     * @return string game state
     *
     */
    public String msg_state() {

        if (game_state == state.win) {
            return "Congragulations you win !!!";
        } else if (game_state == state.lose) {

            return "Unlucky you have lost";
        }
        return "The game is still pending!!!";

    }

    private void checkForwin() {
        Boolean stateB = true;
        for (int i = 0; i < game_rows; i++) {
            for (int j = 0; j < game_rows; j++) {
                if (game_board[i][j] != -1 && game_board[i][j] != -2) {
                    if (hidden[i][j] == false) {
                        stateB = false;
                    }
                }
            }
        }
        if (stateB) {
            game_state = state.win;
        }
    }

    /**
     * method play => play a move (x,y )
     * 
     * @param x    int acturaly y = x-axis , x = y-axis
     * @param y    int
     * @param anim boolean anim=>animation (true)super bomb animation do not reveal
     *             if 0
     */
    public void play(int x, int y, boolean anim) {
        try {

            hidden[x][y] = true;
            ++count;
            if (game_board[x][y] == -1 || game_board[x][y] == -2) {
                game_state = state.lose;
            } else if (game_board[x][y] == 0 && !anim) {
                reveal(x, y);
            }
            checkForwin();
            // if(not_bombs == count){
            // game_state = state.win ;
            // }

        } catch (NullPointerException e) {
            System.out.println("You have not selected a game mode");
        }

    }

    /**
     * method win_or_loss()
     * 
     * @return true(player wins) false(player lose)
     */
    public boolean win_or_loss() {
        if (game_state == state.win) {
            return true;
        }

        return false;

    }

    /**
     * method gethidden see if possistions is hidden or not(played)
     * 
     * @param i int (y)
     * @param j int (x)
     * @return true(revealed) false(hidden)
     */
    public boolean gethidden(int i, int j) {
        return hidden[i][j];
    }

    /**
     * method show_board() => print board
     */
    public void show_board() {
        try {
            // game_senario.print();

            System.out.println();
            for (int i = 0; i < game_rows; i++) {
                if (i == 0) {
                    System.out.printf("   ");
                    for (int j = 0; j < game_rows; j++) {
                        if (j < 10) {
                            System.out.printf("  %d", j);
                        } else {
                            System.out.printf(" %d", j);
                        }
                    }
                    System.out.println();
                }
                if (i < 10) {
                    System.out.printf("%d  ", i);
                } else {
                    System.out.printf("%d ", i);
                }
                for (int j = 0; j < game_rows; j++) {

                    int x = game_board[i][j];
                    if (!hidden[i][j]) {
                        System.out.printf("  X");
                    } else if (x >= 0) {
                        System.out.printf("  %d", x);
                    } else {
                        System.out.printf(" %d", x);
                    }
                }
                System.out.println();
            }

        } catch (NullPointerException e) {
            System.out.println("You have not selected a game mode");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * method setGameLose()
     * game is lost ;
     */
    public void setGameLose() {
        game_state = state.lose;
    }
}
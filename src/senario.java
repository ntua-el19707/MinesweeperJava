package com.vic;

import com.vic.randomNum;

/**
 * class senario
 * 
 * @author Victoras Giannaki
 */
public abstract class senario {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    protected int startx;
    protected int starty;
    protected int senario_tb[][];
    protected int rows;
    protected int upperbound;
    private int to_win;

    /**
     * method set
     * 
     * @param x int
     * @param y int
     *          set start
     */
    // ? avoid player picking a bomb in first move
    public void set(int x, int y) {
        startx = x;
        starty = y;

    }

    /**
     * method pr() print start
     */
    // TODO delete pr its not nessesary
    public void pr() {
        // System.out.println(startx+ " "+starty);
    }

    private void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                senario_tb[i][j] = 0;
            }
        }
    }

    // gen_bombs genarate bomds = num
    protected void gen_Bomb(int num) {
        randomNum genarator = new randomNum(upperbound);// vrate a genarator of random
        // System.out.printf("(%d,%d)\n",startx,starty);
        to_win = upperbound - num;
        int count = 0;
        int p; // possition
        int x;
        int y; // point
        while (count < num) {
            boolean check = false;
            p = genarator.get_Num();
            y = p / rows;
            x = p - y * rows;// create my random point
            // System.out.println(x + " "+y + " "+ startx +" " +starty);
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (x + i == startx && starty == y + j) {
                   //     System.out.println("catch");
                        check = true;
                    }
                }
            }

            if (check == true) {
                continue;
            }
            if (senario_tb[x][y] != -1) {
                // there is no bomb
                senario_tb[x][y] = -1;
                build_neigh(x, y);
                ++count;
            }

        }

    }

    /**
     * method valid_cahange
     * 
     * @param x int
     * @param y int
     * @param cx int
     * @param cy int
     * @return
     */
    public boolean valid_change(int x, int y, int cx, int cy) {
        if (x + cx < 0 || y + cy < 0 || x + cx >= rows || y + cy >= rows) {
            return false;
        } else if (senario_tb[x + cx][y + cy] == -1 || senario_tb[x + cx][y + cy] == -2) {
            return false;
        }
        return true;

    }

    /**
     *  get_not_bombs()
     * @return int  total bombs
     */
    public int get_not_bombs() {
        return to_win;
    }

    private void build_neigh(int x, int y) {

        // 9 changes
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == j && i == 0) {
                    continue;
                }
                if ( valid_change(x, y, i, j)) {
                    // System.out.printf("i %d , j %d \n",x+i,y+j);

                    ++senario_tb[x + i][y + j];
                }
            }

        }
    }

    /**
     * board()
     * @return game Board
     */
    public int[][] board() {
        return senario_tb;
    }

    /**
     * method - get_Rows()
     * @return int "dimensions"
     */
    public int get_Rows() {
        return rows;
    }

    /**
     * method - print()
     */
    public void print() {
        // gen_Bomb(11);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                int x = senario_tb[i][j];

                if (x >= 0) {
                    System.out.printf(" %d", x);
                } else if (x == -2) {
                    System.out.print(ANSI_RED + x + ANSI_RESET);
                } else {
                    System.out.printf("%d", x);
                }

            }
            System.out.println();
        }
    }

}
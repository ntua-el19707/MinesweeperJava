package com.vic;

/**
 * class statics
 * 
 * @author Victoras Giannaki
 *         "a model class for to hold statics of a game"
 */
public class statics {
    private int winner, trials, bombs, time, id, dimensions;
    private int x, y;
    private int board[][];

    /**
     * constructor
     * 
     * @param winner     int
     * @param trials     int "Rounds"
     * @param bombs      int
     * @param time       int
     * @param id         int
     * @param board      int[][] "dimensions*dimenions"
     * @param dimensions int
     * @param x          int
     * @param y          int "Point of lose"
     */
    statics(int winner, int trials, int bombs, int time, int id, int board[][], int dimensions, int x, int y) {
        this.winner = winner;
        this.trials = trials;
        this.time = time;
        this.bombs = bombs;
        this.id = id;
        this.board = board;
        this.dimensions = dimensions;
        this.x = x;
        this.y = y;

    }
    // now getters ;

    /**
     * method - getTrials()
     * 
     * @return int
     *         "Rounds"
     */
    public int getTrials() {
        return trials;
    }

    /**
     * method - getWinner()
     * 
     * @return int
     */
    public int getWinner() {
        return winner;
    }

    /**
     * method - getBombs()
     * 
     * @return int
     */
    public int getBombs() {
        return bombs;
    }

    /**
     * method - getTime()
     * 
     * @return int
     */
    public int getTime() {
        return time;
    }

    /**
     * method - getId()
     * 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * method - getDimensions()
     * 
     * @return int
     */
    public int getDimensions() {
        return dimensions;
    }

    /**
     * method - getBoard()
     * 
     * @return int
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * method - getx()
     * 
     * @return
     */
    public int getx() {
        return x;
    }

    /**
     * method - gety()
     * 
     * @return int
     */
    public int gety() {
        return y;
    }
}
// ? why do i neeed this class ?
// answer
// & 1 to send the data to dile to write
// & 2 ro store in array of statics
// & mulltiple game stats to see in the table
// & or procces them

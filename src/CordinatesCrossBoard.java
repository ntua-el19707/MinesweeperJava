package com.vic;

import java.awt.Point;

/**
 * class CordinatesCrossBoard
 * 
 * @author Vicroras Giannaki
 */
// ? This class is uses to produce the cross points os SuperBomb Animation
public class CordinatesCrossBoard {
    private int x[] = new int[4];
    private int y[] = new int[4];
    private int dimensions = 0;

    /**
     * constructor
     * 
     * @param x          int
     * @param y          int
     * @param dimensions int
     *                   Give(x,y) of SuperBomb and Dimensions of Board
     */

    public CordinatesCrossBoard(int x, int y, int dimensions) {
        // System.out.println(x + " "+ y);
        this.dimensions = dimensions;

        for (int i = 0; i < 4; i++) {
            this.x[i] = x;
        }
        for (int i = 0; i < 4; i++) {
            this.y[i] = y;
        }

    }

    /**
     * method move() calculate the point of the move
     */
    public void move() {
        if (x[0] + 1 < dimensions) {// if 8+1 => deksia
            ++x[0];
        }
        if (y[1] + 1 < dimensions) {// 1+1
            ++y[1];
        }
        if (x[2] - 1 >= 0) {
            --x[2];
        }
        if (y[3] - 1 >= 0) {
            --y[3];
        }
    }

    /**
     * method stop()
     * 
     * @return true(point calculated) false(not finished)
     */
    public boolean stop() {
        if (x[0] == 15 && x[2] == 0 && y[1] == 15 && y[3] == 0) {
            return true;
        }
        return false;
    }

    /**
     * method getRight()
     * 
     * @return Point rightPoint
     */
    public Point getRight() {
        return new Point(x[0], y[0]);
    }

    /**
     * method getUp()
     * 
     * @return Point UpPoint
     */
    public Point getUp() {
        return new Point(x[1], y[1]);
    }

    /**
     * method getleft()
     * 
     * @return Point leftPoint
     */
    public Point getleft() {
        return new Point(x[2], y[2]);
    }

    /**
     * method getDown()
     * 
     * @return Point Down Point
     */
    public Point getDown() {
        return new Point(x[3], y[3]);
    }

}

// & Explain
// etc 1 1 Sp dimensions 15 contructor
/**
 * ? 0 1 2 3 4 5
 * & 0 X X X X X X
 * & 1 X S X X X x
 */
// & move
/**
 * ? 0 1 2 3 4 5
 * & 0 X UP X X X X
 * & 1 L S R X X
 * & 2 X D X X X
 */
// get points
// ? STOP() =>FALSE
// & move
/**
 * ? 0 1 2 3 4 5
 * & 0 X UP X X X X
 * & 1 L S X R X
 * & 2 X X X X X
 * & 3 X D X X
 */
// get points
// ? stop() false
// & repeat until stop() =>true
// * then you get all the points of the collumn and row of point (x,y) set in
// * constuctor

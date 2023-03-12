package com.vic;

import java.util.Random;

/**
 * class randomNum
 * 
 * @author Victoras Giannaki
 */
public class randomNum {
    private Random rand = new Random();
    private int upperbound;

    /**
     * constructor
     * 
     * @param upperbound int
     */
    public randomNum(int upperbound) {
        this.upperbound = upperbound; // upperbound of a genarator
    }

    /**
     * method - get_Num()
     * 
     * @return int <=upperbound
     */
    public int get_Num() {
        return rand.nextInt(upperbound);
    }

}
package com.hwi.health.Usages;

import java.util.Random;

/**
 * Created by Win on 6/14/2017.
 */

public class RandomNumber {


    public String randno(){
        Random rand = new Random();
        int  n = rand.nextInt(50) + 1;
        return n+"";
    }

}

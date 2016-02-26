/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author hakon
 */
import java.net.*;
import java.io.*;
 
public class Protocol {

    private static final int BLINKING_YELLOW = 0;
    private static final int GREEN = 1;
    private static final int YELLOW = 2;
    private static final int RED = 3;

    private static int state = BLINKING_YELLOW;

 
    public static String getState() {
        String theOutput = null;
 
        switch (state) {
            case BLINKING_YELLOW:
                theOutput = "BLINKING_YELLOW";
                break;
            case GREEN:
                theOutput = "GREEN";
                break;
            case YELLOW:
                theOutput = "YELLOW";
                break;
            case RED:
                theOutput = "RED";
                break;
            default:
                theOutput = "BLINKING_YELLOW";
                break;
        }
        return theOutput;
    }
    public static void setGreen(){
        state = GREEN;
    }
    public static void setRed(){
        state = RED;
    }
    public static void setYellow(){
        state = YELLOW;
    }
    public static void setBlinkingYellow(){
        state = BLINKING_YELLOW;
    }
}

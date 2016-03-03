/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hioa.trafficlight;

import java.net.Socket;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hakon
 */
public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }

    public void run() {

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));) {
            String inputLine, outputLine;

            outputLine = Protocol.getState();
            out.println(outputLine);
            String lastState = outputLine;

            while (true) {
                //----------------------
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                //----------------------Det må være et delay her, kan erstattes med System.print.out eller noe annet svada
                System.out.println(Protocol.getState());
                System.out.println(lastState);
                if (!Protocol.getState().equals(lastState)) {
                    System.out.println("Noob!");
                    lastState = Protocol.getState();
                    outputLine = Protocol.getState();
                    out.println(outputLine);
                    if (outputLine.equals("Bye")) {
                        break;
                    }
                }

            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Protocol {

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

        public static void setGreen() {
            state = GREEN;
        }

        public static void setRed() {
            state = RED;
        }

        public static void setYellow() {
            state = YELLOW;
        }

        public static void setBlinkingYellow() {
            state = BLINKING_YELLOW;
        }
    }

}

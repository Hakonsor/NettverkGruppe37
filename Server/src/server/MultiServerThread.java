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

public class MultiServerThread extends Thread {

    private Socket socket = null;

    public MultiServerThread(Socket socket) {
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
                    Logger.getLogger(MultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                //----------------------Det må være et delay her, kan erstattes med System.print.out eller noe annet svada
                if (!Protocol.getState().equals(lastState)) {
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
}

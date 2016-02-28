package model;

import java.io.IOException;

/**
 * Created by Simen on 28.02.2016.
 */
public class Port {
    private final int port;
    public Port (int p) throws InvalidPortException {
        int max = 65535;
        int min = 1023;
        if (p > max || p < min) {
            throw new InvalidPortException();
        } else {
            this.port = p;
        }
    }

    public int getPort() {
        return port;
    }

}

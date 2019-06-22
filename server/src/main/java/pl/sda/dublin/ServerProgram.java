package pl.sda.dublin;

import java.io.IOException;

public class ServerProgram {

    public static void main(String[] args) throws IOException {
        Server mojServer = new Server();
        while (true) {
            mojServer.acceptConnections();
        }
    }
}

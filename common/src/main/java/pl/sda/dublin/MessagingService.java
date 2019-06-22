package pl.sda.dublin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class MessagingService {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public MessagingService(Socket socket) {
        this.socket = requireNonNull(socket);
    }

    public void sendObject(Object message) throws IOException {
        if (isNull(outputStream)) {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        outputStream.writeObject(message);
    }

    public Object readObject() throws IOException, ClassNotFoundException {
        if (isNull(inputStream)) {
            inputStream = new ObjectInputStream(socket.getInputStream());
        }
        return inputStream.readObject();
    }


}

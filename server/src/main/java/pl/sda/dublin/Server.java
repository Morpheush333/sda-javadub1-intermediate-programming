package pl.sda.dublin;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    private ServerSocket serverSocket;
    private final static int SERVER_PORT = 5000;
    private final static int MAX_SERVER_CONNECTIONS = 10;
    private MessagingService messagingService;
    private Conversation conversation = new Conversation();

    /**
     * Tworzy domyslne gniazdo serwera - do dzialania w trybie developerskim na wlasnej maszynie
     * Przyjmuje domyslne parametry
     * Nasluchuje na porcie 5000 na lokalnym adresie i max polaczen to 10
     *
     * @throws IOException
     */
    public Server() throws IOException {
        this(SERVER_PORT, MAX_SERVER_CONNECTIONS, InetAddress.getByName("localhost"));
    }


    /**
     * Tworzy gniazdo na domyslnym porcie 5000 i max 10 polaczeń
     * i z adresem podanym przez programiste jako parametr
     *
     * @param address
     * @throws IOException
     */
    public Server(InetAddress address) throws IOException {
        this(5000, 10, address);
    }


    // drugi konstruktor parametrowy
    //
    public Server(int port, int maxConnections, InetAddress address) throws IOException {
        serverSocket = new ServerSocket(port, maxConnections, address);


    }


    // głowna metoda serwera
    // jej zadaniem jest zaakceptowanie połączenia
    // i utworznie dla kazdego nowego klienta nowego wątku
    // z każdym klientem związany jest ClientService - opakowanie dla obsługi zdarzeń w kliencie
    public void acceptConnections() {
        try {
            System.out.println("Oczekuje na polaczenie na adresie: " + serverSocket.getLocalSocketAddress().toString());

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // jesli max liczba polaczen przekroczona to zwrocony zostanie null
                if (clientSocket == null) {
                    System.out.println("Client socket is null... Koniec nasluchiwania");
                    break;
                }

                System.out.println("Klient sie podlaczyl: ");
                System.out.println("Port: " + clientSocket.getPort());
                System.out.println("LocalPort : " + clientSocket.getLocalPort());
                System.out.println("Address: " + clientSocket.getInetAddress().toString());

                Thread clientThread = new Thread(() -> {
                    ClientService clientService = new ClientService(clientSocket, conversation);
                    if (conversation.getFirstClient() == null) {
                        conversation.setFirstClient(clientService);
                    } else {
                        conversation.setSecondClient(clientService);
                    }
                    clientService.handleCommunication();
                });
                clientThread.start();

                System.out.println("--------------------------------------------\n\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

}

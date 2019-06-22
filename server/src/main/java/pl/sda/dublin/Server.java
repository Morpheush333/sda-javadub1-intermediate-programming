package pl.sda.dublin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    private ServerSocket serverSocket;
    private final static int SERVER_PORT = 5000;
    private final static int MAX_SERVER_CONNECTIONS = 10;

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


    public void acceptConnections() throws IOException {
        System.out.println("Oczekuje na polaczenie na adresie: " + serverSocket.getLocalSocketAddress().toString());
        Socket clientSocket = serverSocket.accept();

        System.out.println("Klient sie podlaczyl: ");
        System.out.println("Port: " + clientSocket.getPort());
        System.out.println("LocalPort : " + clientSocket.getLocalPort());
        System.out.println("Address: " + clientSocket.getInetAddress().toString());


        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println(reader.readLine());

//        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
//        writer.write("Hello from server\n");
//        writer.flush();
        System.out.println("--------------------------------------------\n\n");
    }

}

package com.itismeucci;

import java.net.*;
import java.util.ArrayList;

public class SocketServer {
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ServerSocket server;

    public void listen() {
        try {
            server = new ServerSocket(8080);
            while (true) {
                Socket client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                clients.add(handler);
                handler.start();
            }
        } catch (Exception e) {
        }
    }

    public static void disconnectAll() {
        for (ClientHandler c : clients) {
            try {
                c.getClient().close();
            } catch (Exception e) {

            }
        }
    }

    public static ServerSocket getServer() {
        return SocketServer.server;
    }

    public static ArrayList<ClientHandler> getClients() {
        return SocketServer.clients;
    }
}

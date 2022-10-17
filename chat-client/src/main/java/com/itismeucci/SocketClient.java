package com.itismeucci;

import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner keyboard = new Scanner(System.in);

    public Socket connect() throws InterruptedException {
        try {
            this.socket = new Socket(InetAddress.getLocalHost(), 8080);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Server offline, tento la riconnessione...");
            Thread.sleep(5000);
        }
        return this.socket;
    }

    public void sendMessage() {
        while (true) {
            try {
                System.out.print(socket.getInetAddress().getHostName() + "> ");
                String msg = this.keyboard.nextLine();
                if (msg.equals("FINE")) {
                    this.socket.close();
                    System.out.println("Comunicazione terminata");
                    break;
                }
                this.out.writeBytes(msg + '\n');
                System.out.println("Server> " + this.in.readLine());
            } catch (Exception e) {
                System.out.println("Il server è stato spento");
                return;
            }
        }
    }

    public Socket getSocket()
    {
        return this.socket;
    }
}

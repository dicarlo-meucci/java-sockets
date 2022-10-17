package com.itismeucci;

import java.net.*;
import java.io.*;

public class ClientHandler extends Thread {
    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run()
    {
        boolean handled = handleClient(client);
        while (handled)
        {
            handled = handleClient(client);
        }
    }

    private boolean handleClient(Socket client) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            String recv = in.readLine();
            if (recv.equals("FINE"))
            {
                client.close();
            }
            if (recv.equals("SPEGNI"))
            {
                out.writeBytes("Chiudo il server e disconnetto tutti i socket" + "\n");
                SocketServer.disconnectAll();
                SocketServer.getServer().close();
            }
            System.out.println(client.getInetAddress().getHostName() + "> " + recv);
            String modifiedRecv = recv.toUpperCase();
            out.writeBytes(modifiedRecv + '\n');
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Socket getClient()
    {
        return this.client;
    }
}

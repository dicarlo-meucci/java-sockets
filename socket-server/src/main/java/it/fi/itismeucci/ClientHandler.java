package it.fi.itismeucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket client;

    public ClientHandler(Socket s)
    {
        this.client = s;
    }

    public void run()
    {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            String recv = in.readLine();
            System.out.print("Stringa ricevuta: " + recv);
            String modifiedRecv = recv.toUpperCase();
            out.writeBytes(modifiedRecv + '\n');
            client.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

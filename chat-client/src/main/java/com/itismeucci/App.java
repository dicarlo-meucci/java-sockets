package com.itismeucci;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SocketClient client = new SocketClient();
        while (client.getSocket() == null)
        {
            client.connect();
        }
        client.sendMessage();
    }
}

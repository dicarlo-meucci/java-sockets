package com.itismeucci;

public class App 
{
    public static void main( String[] args )
    {
        SocketServer server = new SocketServer();
        server.listen();
    }
}

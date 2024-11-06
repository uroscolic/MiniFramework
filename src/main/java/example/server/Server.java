package example.server;

import example.RouteMapper;
import example.myPackage.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int TCP_PORT = 8080;


    public static void run(RouteMapper routeMapper) {
        try {
            ServerSocket serverSocket = new ServerSocket(TCP_PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerThread(socket, routeMapper)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

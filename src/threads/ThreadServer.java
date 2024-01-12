/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 38169
 */
public class ThreadServer extends Thread{

 private ServerSocket serverSocket;

    public ThreadServer() {
        try {
            serverSocket = new ServerSocket(11000);
        } catch (IOException ex) {
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
               
            try {
                 while (!serverSocket.isClosed()) {    
                Socket socket= serverSocket.accept();
                     System.out.println("Klijent se povezao!");
                     ThreadClient th= new ThreadClient(socket);
                     th.start();
                 }
            } catch (IOException ex) {
            }
        
    }
    
}

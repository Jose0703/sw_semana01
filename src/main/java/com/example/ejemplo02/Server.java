package com.example.ejemplo02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 13;

    public Server() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println(" 1 > Server started at port " + PORT);
            while (true) {
                System.out.println("2 > Waiting for client...");
                Socket client = server.accept();
                System.out.println("3 > Client connected from " + client.getInetAddress());
                
                //Flujos de comunicación
                BufferedReader entrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter salida = new PrintWriter(client.getOutputStream(), true);

                //Leer mensaje de cliente
                String mensaje = entrada.readLine();

                //Procesar mensaje
                String precio = "";
                switch (mensaje) {
                    case "PLATEA":  precio = "50";  break;
                    case "VIP":  precio = "150";  break;
                    case "PLATINIUM":  precio = "250";  break;
                }
                //Enviar mensaje al cliente
                System.out.println("4 > El precio del tipo de cliente " + mensaje + " es " + precio);
                salida.println("El precio del tipo de cliente " + mensaje + " es " + precio);
                
                System.out.println("5 > Closing connection...");
                client.close();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        new Server();
    }

}

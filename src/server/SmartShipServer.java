package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartShipServer {
    private static final int PORT = 5000;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        System.out.println("🚀 SmartShip Server starting on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            DatabaseConnection.getConnection(); // ensure DB connection
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("🧑‍💻 New client connected: " + clientSocket.getInetAddress());
                ClientHandler handler = new ClientHandler(clientSocket);
                pool.execute(handler);
            }
        } catch (IOException e) {
            System.err.println("❌ Server error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
        }
    }

    public static ExecutorService getPool() {
        return pool;
    }

    public static void setPool(ExecutorService pool) {
        SmartShipServer.pool = pool;
    }
}

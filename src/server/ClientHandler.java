package server;

import java.io.*;
import java.net.*;
import java.sql.*;

/**
 * Handles client requests on separate threads.
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Connection conn;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            this.conn = DatabaseConnection.getConnection();
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String command = (String) in.readObject();
                System.out.println("📩 Received: " + command);

                switch (command.toLowerCase()) {
                    case "register" -> handleRegister();
                    case "login" -> handleLogin();
                    case "exit" -> {
                        System.out.println("Client disconnected.");
                        socket.close();
                        return;
                    }
                    default -> out.writeObject("❌ Invalid command");
                }
            }
        } catch (Exception e) {
            System.err.println("⚠️ Client disconnected: " + e.getMessage());
        }
    }

    private void handleRegister() {
        try {
            String name = (String) in.readObject();
            String email = (String) in.readObject();
            String password = (String) in.readObject();
            String role = (String) in.readObject();

            String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.executeUpdate();

            out.writeObject("✅ Registration successful for " + name);
        } catch (Exception e) {
            try { out.writeObject("❌ Registration failed: " + e.getMessage()); } catch (IOException ex) {}
        }
    }

    private void handleLogin() {
        try {
            String email = (String) in.readObject();
            String password = (String) in.readObject();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.writeObject("✅ Login successful! Welcome, " + rs.getString("name"));
            } else {
                out.writeObject("❌ Invalid email or password");
            }
        } catch (Exception e) {
            try { out.writeObject("❌ Login failed: " + e.getMessage()); } catch (IOException ex) {}
        }
    }
}

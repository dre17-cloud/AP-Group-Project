package model;

import java.util.ArrayList;

public class Manager extends User {
    private ArrayList<Vehicle> fleet;
    private ArrayList<User> users;

    public Manager(String userId, String name, String email, String password) {
        super(userId, name, email, password, "Manager");
        fleet = new ArrayList<>();
        users = new ArrayList<>();
    }

    // User Management
    public void addUser(User user) {
        users.add(user);
        System.out.println("User " + user.getName() + " added successfully.");
    }

    public void removeUser(String userId) {
        users.removeIf(u -> u.getUserId().equals(userId));
        System.out.println("User with ID " + userId + " removed.");
    }

    // Fleet Management
    public void addVehicle(Vehicle v) {
        fleet.add(v);
        System.out.println("Vehicle " + v.getVehicleId() + " added to fleet.");
    }

    // Generate simple report
    public void generateReport(ArrayList<Shipment> shipments) {
        System.out.println("\n=== SMARTSHIP REPORT ===");
        int total = shipments.size();
        long delivered = shipments.stream().filter(s -> s.getStatus().equalsIgnoreCase("Delivered")).count();
        long inTransit = shipments.stream().filter(s -> s.getStatus().equalsIgnoreCase("In Transit")).count();
        long pending = shipments.stream().filter(s -> s.getStatus().equalsIgnoreCase("Pending")).count();

        System.out.println("Total Shipments: " + total);
        System.out.println("Delivered: " + delivered);
        System.out.println("In Transit: " + inTransit);
        System.out.println("Pending: " + pending);
        System.out.println("============================");
    }

    @Override
    public void showMenu() {
        System.out.println("===== MANAGER MENU =====");
        System.out.println("1. Manage Users");
        System.out.println("2. Manage Vehicles");
        System.out.println("3. Generate Reports");
    }
}

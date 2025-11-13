package main;

import java.io.IOException;
import models.*;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Shipment> shipments = new ArrayList<>();
    private static final ArrayList<Invoice> invoices = new ArrayList<>();
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static final ArrayList<Customer> customers = new ArrayList<>();

    private static final String MANAGER_USERNAME = "manager";
    private static final String MANAGER_PASSWORD = "admin123";

    public static void main(String[] args) {

        vehicles.add(new Vehicle("VH001", 300, 20));

        while (true) {
            clearScreen();
            System.out.println("\n===== SMARTSHIP MAIN MENU =====");
            System.out.println("1. Customer");
            System.out.println("2. Clerk");
            System.out.println("3. Driver");
            System.out.println("4. Manager");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> customerMenu();
                case 2 -> clerkMenu();
                case 3 -> driverMenu();
                case 4 -> managerLogin();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
            }
        }
    }

    // =====================================================
    // CUSTOMER SECTION
    // =====================================================

    private static void customerMenu() {
        clearScreen();

        System.out.println("===== CUSTOMER REGISTRATION =====");
        String name = readAny("Your name: ");
        String email = readEmail("Email: ");
        String password = readAny("Password: ");

        String regEmail = email;
        String regPass = password;

        Customer customer = new Customer("C" + (customers.size() + 1), name, email, password);
        customers.add(customer);

        System.out.println("Registration successful!");
        pause();

        while (true) {
            clearScreen();
            System.out.println("===== CUSTOMER LOGIN =====");

            String logEmail = readAny("Email: ");
            String logPass = readAny("Password: ");

            if (logEmail.equalsIgnoreCase(regEmail) && logPass.equals(regPass)) {
                System.out.println("Login successful!");
                pause();
                customerPortal(customer);
                return;
            }

            System.out.println("Invalid login. Try again.");
            pause();
        }
    }

    private static void customerPortal(Customer customer) {
        while (true) {
            clearScreen();
            System.out.println("\n===== CUSTOMER PORTAL =====");
            System.out.println("1. Create Shipment");
            System.out.println("2. Track Shipment");
            System.out.println("3. View Invoices");
            System.out.println("4. Logout");

            int choice = readInt();

            switch (choice) {

                case 1 -> {
                    String recipient = readAny("Recipient: ");
                    String dest = readAny("Destination: ");
                    int zone = readIntRange("Zone (1–4): ", 1, 4);
                    double weight = readDouble("Weight (kg): ", 0.1);
                    String type = readAny("Type (Standard/Express/Fragile): ");

                    try{
                    Shipment s = new BaseShipment(customer.getName(), recipient, dest, zone, weight, type);
                    shipments.add(s);
                    customer.createShipment(s);

                    
                    Invoice inv = new Invoice(s);
                    invoices.add(inv);

                    System.out.println("Shipment created!");
                    s.printDetails();
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error creating shipment:" +ex.getMessage());
                    }
                    pause();
                }

                case 2 -> {
                    String tn = readAny("Tracking #: ");
                    customer.trackShipment(tn);
                    pause();
                }

                case 3 -> {
                    boolean found = false;

                    for (Invoice inv : invoices) {

                        BaseShipment bs = (BaseShipment) inv.getShipment();

                        if (bs.getSenderName().equalsIgnoreCase(customer.getName())) {
                            inv.printInvoice();
                            found = true;
                        }
                    }

                    if (!found)
                        System.out.println("No invoices found.");

                    pause();
                }

                case 4 -> { return; }
            }
        }
    }

    // =====================================================
    // CLERK SECTION
    // =====================================================

    private static void clerkMenu() {
        while (true) {
            clearScreen();
            System.out.println("\n===== CLERK PORTAL =====");
            System.out.println("1. View Shipments");
            System.out.println("2. Assign Shipment to Vehicle");
            System.out.println("3. Generate Invoice");
            System.out.println("4. Record Payment");
            System.out.println("5. Logout");

            int ch = readInt();

            switch (ch) {

                case 1 -> {
                    if (shipments.isEmpty())
                        System.out.println("No shipments.");
                    else
                        shipments.forEach(Shipment::printDetails);

                    pause();
                }

                case 2 -> assignShipmentToVehicle();

                case 3 -> generateInvoiceManual();

                case 4 -> recordPayment();

                case 5 -> { return; }
            }
        }
    }

    private static void assignShipmentToVehicle() {

        String tn = readAny("Tracking #: ");

        Shipment s = shipments.stream()
                .filter(x -> x.getTrackingNumber().equalsIgnoreCase(tn))
                .findFirst().orElse(null);

        if (s == null) {
            System.out.println("Shipment not found.");
            pause();
            return;
        }

        BaseShipment bs = (BaseShipment) s;

        System.out.println("Vehicles:");
            for (Vehicle v : vehicles) {
    System.out.println(
            v.getVehicleId() +
            " | Packages: " + v.getShipments().size() + "/" + v.getMaxPackages() +
            " | MaxWeight: " + v.getMaxWeight()
    );
}
                String vid = readAny("Vehicle ID: ");

                Vehicle v = vehicles.stream()
                .filter(x -> x.getVehicleId().equalsIgnoreCase(vid))
                .findFirst().orElse(null);

        if (v == null) {
            System.out.println("Vehicle not found.");
            pause();
            return;
        }

      if (v.getShipments().size() >= v.getMaxPackages()) {
        System.out.println("Vehicle package capacity full!");
        pause();
        return;
    }

    if (bs.getWeight() > v.getMaxWeight()) {
        System.out.println("Weight exceeds vehicle limit!");
        pause();
        return;
}

v.getShipments().add(s);
s.setStatus("Assigned");

System.out.println("Shipment assigned!");
pause();
    }

    private static void generateInvoiceManual() {
        String tn = readAny("Tracking #: ");

        Shipment s = shipments.stream()
                .filter(x -> x.getTrackingNumber().equalsIgnoreCase(tn))
                .findFirst().orElse(null);

        if (s == null) {
            System.out.println("Shipment not found.");
            pause();
            return;
        }

        
        Invoice inv = new Invoice(s);
        invoices.add(inv);

        inv.printInvoice();
        pause();
    }

    private static void recordPayment() {
        String id = readAny("Invoice #: ");

        Invoice inv = invoices.stream()
                .filter(x -> x.getInvoiceNumber().equalsIgnoreCase(id))
                .findFirst().orElse(null);

        if (inv == null) {
            System.out.println("Invoice not found.");
            pause();
            return;
        }

        double amt = readDouble("Payment amount: ", 0.01);
        inv.makePayment(amt);

        pause();
    }

    // =====================================================
    // DRIVER & MANAGER
    // =====================================================

    private static void driverMenu() {
        System.out.println("Driver features coming later.");
        pause();
    }

    private static void managerLogin() {
        String u = readAny("Username: ");
        String p = readAny("Password: ");

        if (u.equals(MANAGER_USERNAME) && p.equals(MANAGER_PASSWORD)) {
            managerMenu();
        } else {
            System.out.println("Invalid credentials.");
            pause();
        }
    }

    private static void managerMenu() {
        while (true) {
            clearScreen();
            System.out.println("===== MANAGER PORTAL =====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicles");
            System.out.println("3. Logout");

            int c = readInt();

            switch (c) {
                case 1 -> {
                    String id = readAny("Vehicle ID: ");
                    double mw = readDouble("Max Weight: ", 1);
                    int mp = readIntRange("Max Packages: ", 1, 500);

                    vehicles.add(new Vehicle(id, mw, mp));
                    pause();
                }

                case 2 -> {
                    vehicles.forEach(System.out::println);
                    pause();
                }

                case 3 -> { return; }
            }
        }
    }

    // =====================================================
    // INPUT HELPERS
    // =====================================================

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter valid number: ");
            }
        }
    }

    private static int readIntRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(scanner.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.println("Out of range.");
            } catch (NumberFormatException ignored) {}
        }
    }

    private static double readDouble(String prompt, double min) {
        while (true) {
            System.out.print(prompt);
            try {
                double v = Double.parseDouble(scanner.nextLine().trim());
                if (v >= min) return v;
                System.out.println("Too small.");
            } catch (NumberFormatException ignored) {}
        }
    }

    private static String readAny(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Cannot be empty.");
        }
    }

    private static String readEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (s.contains("@") && s.contains(".")) return s;
            System.out.println("Invalid email.");
        }
    }

    private static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ignored) {}
    }
}

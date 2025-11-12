package main;

import models.BaseShipment;
import models.Clerk;
import models.Customer;
import models.Driver;
import models.Invoice;
import models.Manager;
import models.Shipment;
import models.Vehicle;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Shipment> shipments = new ArrayList<>();
        ArrayList<Invoice> invoices = new ArrayList<>();

        // Predefined staff members
        Clerk clerk = new Clerk("CL001", "Nicholas Smith", "nick@smartship.com", "clerk123");
        Driver driver = new Driver("D001", "Tavaughn Henry", "tavaughn@smartship.com", "drive123");
        Manager manager = new Manager("M001", "Julia Williams", "julia@smartship.com", "admin123");

        Vehicle vehicle = new Vehicle("VH001", 300, 5);

        System.out.println("===== SMARTSHIP PACKAGE MANAGEMENT SYSTEM =====\n");
        System.out.println("Select your role:");
        System.out.println("1. Customer");
        System.out.println("2. Clerk");
        System.out.println("3. Driver");
        System.out.println("4. Manager");
        System.out.print("Enter your choice: ");
        int roleChoice = sc.nextInt();
        sc.nextLine();

        switch (roleChoice) {
            /* ------------------ CUSTOMER ------------------ */
            case 1 -> {
                System.out.println("\n--- Customer Registration ---");
                System.out.print("Enter your name: ");
                String name = sc.nextLine();
                System.out.print("Enter your email: ");
                String email = sc.nextLine();
                System.out.print("Enter your password: ");
                String password = sc.nextLine();

                Customer customer = new Customer("C001", name, email, password);

                int choice;
                do {
                    System.out.println("\n===== CUSTOMER MENU =====");
                    System.out.println("1. Create Shipment");
                    System.out.println("2. Track Shipment");
                    System.out.println("3. View Invoices");
                    System.out.println("0. Logout");
                    System.out.print("Choose: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1 -> {
                            System.out.print("Recipient Name: ");
                            String rec = sc.nextLine();
                            System.out.print("Destination: ");
                            String dest = sc.nextLine();
                            System.out.print("Zone (1–4): ");
                            int zone = sc.nextInt();
                            System.out.print("Weight (kg): ");
                            double weight = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Type (Standard / Express / Fragile): ");
                            String type = sc.nextLine();

                            Shipment s = new BaseShipment(customer.getName(), rec, dest, zone, weight, type);
                            shipments.add(s);
                            customer.createShipment(s);
                            clerk.processShipment(s);
                            Invoice inv = clerk.generateInvoice(s);
                            invoices.add(inv);
                        }

                        case 2 -> {
                            System.out.print("Enter Tracking Number: ");
                            String track = sc.nextLine();
                            customer.trackShipment(track);
                        }

                        case 3 -> {
                            System.out.println("\n--- Your Invoices ---");
                            for (Invoice inv : invoices) inv.printInvoice();
                        }

                        case 0 -> System.out.println("Logging out...");
                        default -> System.out.println("⚠️ Invalid option.");
                    }
                } while (choice != 0);
            }

            /* ------------------ CLERK ------------------ */
            case 2 -> {
                int choice;
                do {
                    System.out.println("\n===== CLERK MENU =====");
                    System.out.println("1. View Shipments");
                    System.out.println("2. Generate Invoice");
                    System.out.println("0. Logout");
                    System.out.print("Choose: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1 -> {
                            if (shipments.isEmpty()) System.out.println("No shipments yet.");
                            else for (Shipment s : shipments) s.printDetails();
                        }

                        case 2 -> {
                            System.out.print("Enter tracking number: ");
                            String tn = sc.nextLine();
                            for (Shipment s : shipments) {
                                if (s.getTrackingNumber().equalsIgnoreCase(tn)) {
                                    Invoice inv = clerk.generateInvoice(s);
                                    invoices.add(inv);
                                }
                            }
                        }

                        case 0 -> System.out.println("Logging out...");
                        default -> System.out.println("⚠️ Invalid option.");
                    }
                } while (choice != 0);
            }

            /* ------------------ DRIVER ------------------ */
            case 3 -> {
                driver.assignVehicle(vehicle);
                int choice;
                do {
                    System.out.println("\n===== DRIVER MENU =====");
                    System.out.println("1. View Deliveries");
                    System.out.println("2. Update Delivery Status");
                    System.out.println("0. Logout");
                    System.out.print("Choose: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1 -> driver.viewDeliveries();
                        case 2 -> {
                            System.out.print("Enter tracking number: ");
                            String tn = sc.nextLine();
                            System.out.print("Enter new status (In Transit / Delivered): ");
                            String ns = sc.nextLine();
                            driver.updateStatus(tn, ns);
                        }
                        case 0 -> System.out.println("Logging out...");
                        default -> System.out.println("⚠️ Invalid option.");
                    }
                } while (choice != 0);
            }

            /* ------------------ MANAGER ------------------ */
            case 4 -> {
                int choice;
                do {
                    System.out.println("\n===== MANAGER MENU =====");
                    System.out.println("1. Add Vehicle");
                    System.out.println("2. Generate Report");
                    System.out.println("0. Logout");
                    System.out.print("Choose: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1 -> {
                            System.out.print("Vehicle ID: ");
                            String vid = sc.nextLine();
                            System.out.print("Max Weight: ");
                            double w = sc.nextDouble();
                            System.out.print("Max Packages: ");
                            int p = sc.nextInt();
                            sc.nextLine();
                            Vehicle v = new Vehicle(vid, w, p);
                            manager.addVehicle(v);
                        }

                        case 2 -> manager.generateReport(shipments);

                        case 0 -> System.out.println("Logging out...");
                        default -> System.out.println("⚠️ Invalid option.");
                    }
                } while (choice != 0);
            }

            /* ------------------ INVALID ------------------ */
            default -> System.out.println("⚠️ Invalid role selected.");
        }

        sc.close();
        System.out.println("\n===== END OF PROGRAM =====");
    }
}

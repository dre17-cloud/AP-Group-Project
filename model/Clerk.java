package model;

import java.util.ArrayList;

public class Clerk extends User {
    private ArrayList<Shipment> pendingShipments;

    public Clerk(String userId, String name, String email, String password) {
        super(userId, name, email, password, "Clerk");
        pendingShipments = new ArrayList<>();
    }

    // Process new shipment requests from customers
    public void processShipment(Shipment shipment) {
        if (shipment.getStatus().equalsIgnoreCase("Pending")) {
            shipment.setStatus("Processed");
            pendingShipments.add(shipment);
            System.out.println(" Shipment " + shipment.getTrackingNumber() + " processed successfully.");
        } else {
            System.out.println(" Shipment already processed or invalid status.");
        }
    }

    // Assign shipment to a vehicle
    public void assignShipmentToVehicle(Shipment shipment, Vehicle vehicle) {
        boolean success = vehicle.assignShipment(shipment);
        if (success) {
            System.out.println("Shipment " + shipment.getTrackingNumber() + " assigned to vehicle " + vehicle.getVehicleId());
        }
    }

    // Manage payment
    public Invoice generateInvoice(Shipment shipment) {
        Invoice inv = new Invoice(shipment);
        System.out.println("🧾 Invoice generated for shipment " + shipment.getTrackingNumber());
        return inv;
    }

    @Override
    public void showMenu() {
        System.out.println("===== CLERK MENU =====");
        System.out.println("1. Process Shipment");
        System.out.println("2. Assign Shipment to Vehicle");
        System.out.println("3. Generate Invoice");
    }
}


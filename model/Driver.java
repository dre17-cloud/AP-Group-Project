package model;

import java.util.ArrayList;

public class Driver extends User {
    private ArrayList<Shipment> assignedDeliveries;
    private Vehicle assignedVehicle;

    // Constructors
    public Driver() {
        super();
        this.role = "Driver";
        assignedDeliveries = new ArrayList<>();
    }

    public Driver(String userId, String name, String email, String password) {
        super(userId, name, email, password, "Driver");
        assignedDeliveries = new ArrayList<>();
    }

    public Driver(Driver other) {
        super(other);
        this.assignedDeliveries = new ArrayList<>(other.assignedDeliveries);
    }

    public void assignVehicle(Vehicle vehicle) {
        this.assignedVehicle = vehicle;
        System.out.println("Vehicle " + vehicle.getVehicleId() + " assigned to driver " + getName());
    }

    public void viewAssignedDeliveries() {
        if (assignedVehicle == null) {
            System.out.println(" No vehicle assigned yet.");
            return;
        }
        assignedVehicle.listAssignedShipments();
    }

    public void updateDeliveryStatus(String trackingNumber, String status) {
        for (Shipment s : assignedVehicle.getAssignedShipments()) {
            if (s.getTrackingNumber().equals(trackingNumber)) {
                s.setStatus(status);
                System.out.println("Shipment " + trackingNumber + " updated to: " + status);
                return;
            }
        }
        System.out.println("Shipment not found in your list.");
    }

    public void addAssignedShipment(Shipment shipment) {
        assignedDeliveries.add(shipment);
    }

    @Override
    public void showMenu() {
        System.out.println("===== DRIVER MENU =====");
        System.out.println("1. View Assigned Deliveries");
        System.out.println("2. Update Delivery Status");
    }
}

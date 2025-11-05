package model;

import java.util.ArrayList;

public class Vehicle {
    private String vehicleId;
    private double maxWeight;
    private int maxPackages;
    private ArrayList<Shipment> assignedShipments;

    //Constructors
    public Vehicle() {
        this.vehicleId = "UNKNOWN";
        this.maxWeight = 0.0;
        this.maxPackages = 0;
        assignedShipments = new ArrayList<>();
    }

    public Vehicle(String vehicleId, double maxWeight, int maxPackages) {
        this.vehicleId = vehicleId;
        this.maxWeight = maxWeight;
        this.maxPackages = maxPackages;
        assignedShipments = new ArrayList<>();
    }

    public Vehicle(Vehicle other) {
        this.vehicleId = other.vehicleId;
        this.maxWeight = other.maxWeight;
        this.maxPackages = other.maxPackages;
        this.assignedShipments = new ArrayList<>(other.assignedShipments);
    }

    public boolean assignShipment(Shipment shipment) {
        double totalWeight = assignedShipments.stream().mapToDouble(Shipment::getWeight).sum();
        if (assignedShipments.size() < maxPackages && totalWeight + shipment.getWeight() <= maxWeight) {
            assignedShipments.add(shipment);
            shipment.setStatus("Assigned");
            return true;
        }
        return false;
    }

    public void listAssignedShipments() {
        if (assignedShipments.isEmpty()) {
            System.out.println("No shipments assigned.");
            return;
        }
        for (Shipment s : assignedShipments) {
            System.out.println(" - " + s.getTrackingNumber() + " (" + s.getStatus() + ")");
        }
    }

    public String getVehicleId() { return vehicleId; }
    public ArrayList<Shipment> getAssignedShipments() { return assignedShipments; }
}

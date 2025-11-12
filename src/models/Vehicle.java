package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String vehicleId;
    private double maxWeight;
    private int maxPackages;
    private ArrayList<Shipment> assignedShipments;

    public Vehicle(String vehicleId, double maxWeight, int maxPackages) {
        this.vehicleId = vehicleId;
        this.maxWeight = maxWeight;
        this.maxPackages = maxPackages;
        assignedShipments = new ArrayList<>();
    }

    public boolean assignShipment(Shipment s) {
        if (assignedShipments.size() >= maxPackages) {
            System.out.println("Cannot assign shipment — vehicle full!");
            return false;
        }
        assignedShipments.add(s);
        s.setStatus("Assigned");
        return true;
    }

    public void listAssignedShipments() {
        System.out.println(" Vehicle " + vehicleId + " Assignments:");
        for (Shipment s : assignedShipments)
            System.out.println(" - " + s.getTrackingNumber() + " (" + s.getStatus() + ")");
    }

    public String getVehicleId() { return vehicleId; }
    public ArrayList<Shipment> getAssignedShipments() { return assignedShipments; }
}

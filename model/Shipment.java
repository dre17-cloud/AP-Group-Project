package model;

public class Shipment {
    private static int trackingCounter = 1; // Keeps track of number of shipments created

    private String trackingNumber;
    private String senderName;
    private String recipientName;
    private String destination;
    private int zone;
    private double weight;
    private String type;   // Standard, Express, Fragile
    private String status; // Pending, Assigned, In Transit, Delivered, Cancelled
    private double cost;

    // Constructor
    public Shipment(String senderName, String recipientName, String destination, int zone, double weight, String type) {
        this.trackingNumber = generateTrackingNumber(); // auto-generate tracking number
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.destination = destination;
        this.zone = zone;
        this.weight = weight;
        this.type = type;
        this.status = "Pending";
        this.cost = calculateCost();
    }

    // Generate tracking number automatically
    private String generateTrackingNumber() {
        String number = "TRK" + String.format("%04d", trackingCounter);
        trackingCounter++; // Increment for next shipment
        return number;
    }

    // Calculate cost based on weight, zone, and type
    private double calculateCost() {
        double baseRate = 500; // Base rate in JMD
        double zoneMultiplier = 1 + (zone * 0.3);
        double typeMultiplier = switch (type.toLowerCase()) {
            case "express" -> 1.5;
            case "fragile" -> 1.3;
            default -> 1.0;
        };
        return baseRate * zoneMultiplier * typeMultiplier * (weight / 2);
    }

    // Getters and Setters
    public String getTrackingNumber() { return trackingNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getCost() { return cost; }
    public double getWeight() { return weight; }
    public int getZone() { return zone; }

    // Print shipment details
    public void printDetails() {
        System.out.println("Tracking #: " + trackingNumber);
        System.out.println("Sender: " + senderName);
        System.out.println("Recipient: " + recipientName);
        System.out.println("Destination: " + destination + " (Zone " + zone + ")");
        System.out.println("Weight: " + weight + "kg");
        System.out.println("Type: " + type);
        System.out.println("Cost: $" + cost);
        System.out.println("Status: " + status);
    }
}

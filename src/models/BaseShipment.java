package models;

/**
 * Concrete implementation of the Shipment interface.
 */
public class BaseShipment implements Shipment {
    private static final long serialVersionUID = 1L;
    private static int trackingCounter = 1;
    private String trackingNumber;
    private String senderName;
    private String recipientName;
    private String destination;
    private int zone;
    private double weight;
    private String type;   // Standard, Express, Fragile
    private String status; // Pending, Assigned, In Transit, Delivered
    private double cost;

    public BaseShipment(String senderName, String recipientName, String destination, int zone, double weight, String type) {
        this.trackingNumber = generateTrackingNumber();
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.destination = destination;
        this.zone = zone;
        this.weight = weight;
        this.type = type;
        this.status = "Pending";
        this.cost = calculateCost();
    }

    private String generateTrackingNumber() {
        return "TRK" + String.format("%04d", trackingCounter++);
    }

    private double calculateCost() {
        double baseRate = 500; // JMD
        double zoneMultiplier = 1 + (zone * 0.3);
        double typeMultiplier = switch (type.toLowerCase()) {
            case "express" -> 1.5;
            case "fragile" -> 1.3;
            default -> 1.0;
        };
        return baseRate * zoneMultiplier * typeMultiplier * (weight / 2);
    }

    @Override public String getTrackingNumber() { return trackingNumber; }
    @Override public String getStatus() { return status; }
    @Override public void setStatus(String status) { this.status = status; }
    @Override public double getCost() { return cost; }

    @Override
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

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

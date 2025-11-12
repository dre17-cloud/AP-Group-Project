package models;


import java.util.ArrayList;

public class Customer extends User  {
    private static final long serialVersionUID = 1L;
    private ArrayList<Shipment> shipments;

    public Customer() {
        super();
        this.role = "Customer";
        shipments = new ArrayList<>();
    }

    public Customer(String userId, String name, String email, String password) {
        super(userId, name, email, password, "Customer");
        shipments = new ArrayList<>();
    }

    public void createShipment(Shipment shipment) {
        shipments.add(shipment);
        System.out.println("✅ Shipment created successfully!");
    }

    public void trackShipment(String trackingNumber) {
        for (Shipment s : shipments) {
            if (s.getTrackingNumber().equals(trackingNumber)) {
                System.out.println("Shipment Status: " + s.getStatus());
                return;
            }
        }
        System.out.println("⚠️ Tracking number not found.");
    }

    @Override
    public void showMenu() {
        System.out.println("===== CUSTOMER MENU =====");
        System.out.println("1. Create Shipment");
        System.out.println("2. Track Shipment");
        System.out.println("3. View Invoices");
    }
}

package client.data;

import java.util.ArrayList;

import models.BaseShipment;

public class DriverShipmentDatabase {

    private static final ArrayList<BaseShipment> shipments = new ArrayList<>();

    //TEMPORARY UNTIL WE CREATE DATABASE CONNECTION

    // TEMP: sample data (until Clerk creates shipments)
    static {
        shipments.add(new BaseShipment("John Brown", "Lisa Green", "Kingston", 2, 4.5, "Standard"));
        shipments.add(new BaseShipment("Peter Clarke", "Mark Jones", "Portmore", 1, 2.0, "Express"));
        shipments.add(new BaseShipment("Sarah Hall", "David Shaw", "Spanish Town", 3, 3.2, "Fragile"));
    }

    // Return all shipments (later: filter for driver)
    public static ArrayList<BaseShipment> getAllShipments() {
        return shipments;
    }

    // Update status
    public static boolean updateShipmentStatus(String trackingNumber, String newStatus) {
        for (BaseShipment s : shipments) {
            if (s.getTrackingNumber().equals(trackingNumber)) {
                s.setStatus(newStatus);  // Uses your strict validation
                return true;
            }
        }
        return false;
    }
}


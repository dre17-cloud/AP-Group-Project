package client.gui;

import client.data.DriverShipmentDatabase;
import models.BaseShipment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UpdateStatusFrame extends JFrame {

    private final JComboBox<String> shipmentSelect;
    private final JComboBox<String> statusSelect;

    public UpdateStatusFrame() {
        setTitle("Update Delivery Status");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Load shipments
        ArrayList<BaseShipment> shipments = DriverShipmentDatabase.getAllShipments();
        String[] trackingNumbers = shipments.stream()
                .map(BaseShipment::getTrackingNumber)
                .toArray(String[]::new);

        // Shipment dropdown
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Select Shipment:"), gbc);

        shipmentSelect = new JComboBox<>(trackingNumbers);
        gbc.gridx = 1;
        add(shipmentSelect, gbc);

        // Status dropdown
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("New Status:"), gbc);

        statusSelect = new JComboBox<>(new String[]{
                "Processed",
                "Assigned",
                "In Transit",
                "Delivered"
        });

        gbc.gridx = 1;
        add(statusSelect, gbc);

        // Update button
        JButton updateBtn = new JButton("Update Status");
        gbc.gridx = 0; 
        gbc.gridy = 2; 
        gbc.gridwidth = 2;
        add(updateBtn, gbc);

        updateBtn.addActionListener(e -> updateStatus());
    }

    private void updateStatus() {
        String tracking = shipmentSelect.getSelectedItem().toString();
        String newStatus = statusSelect.getSelectedItem().toString();

        try {
            DriverShipmentDatabase.updateShipmentStatus(tracking, newStatus);
            JOptionPane.showMessageDialog(this, "Status updated successfully!");
            dispose();
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Invalid Transition",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

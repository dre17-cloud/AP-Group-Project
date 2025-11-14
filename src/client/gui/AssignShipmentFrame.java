package client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import models.*;

public class AssignShipmentFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public AssignShipmentFrame(ArrayList<Shipment> shipments, ArrayList<Vehicle> vehicles) {

        setTitle("Assign Shipment to Vehicle");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblShipment = new JLabel("Select Shipment:");
        JLabel lblVehicle = new JLabel("Select Vehicle:");

        JComboBox<Shipment> shipmentBox = new JComboBox<>();
        JComboBox<Vehicle> vehicleBox = new JComboBox<>();

        // Load all shipments
        for (Shipment s : shipments) {
            shipmentBox.addItem(s);
        }

        // Load vehicles
        for (Vehicle v : vehicles) {
            vehicleBox.addItem(v);
        }

        JButton assignBtn = new JButton("Assign");
        JButton cancelBtn = new JButton("Cancel");

        gbc.gridx = 0; 
        gbc.gridy = 0; 
        panel.add(lblShipment, gbc);
        gbc.gridx = 1; 
        panel.add(shipmentBox, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 1; 
        panel.add(lblVehicle, gbc);
        gbc.gridx = 1; 
        panel.add(vehicleBox, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 2; 
        panel.add(assignBtn, gbc);
        gbc.gridx = 1; 
        panel.add(cancelBtn, gbc);

        add(panel);

        assignBtn.addActionListener(e -> {

            Shipment s = (Shipment) shipmentBox.getSelectedItem();
            Vehicle v = (Vehicle) vehicleBox.getSelectedItem();

            if (s == null || v == null) {
                JOptionPane.showMessageDialog(this, "Please select both shipment and vehicle.");
                return;
            }

            BaseShipment bs = (BaseShipment) s;

            // CAPACITY CHECK 
            if (v.getShipments().size() >= v.getMaxPackages()) {
                JOptionPane.showMessageDialog(this, "Vehicle package capacity full!");
                return;
            }

            // WEIGHT CHECK 
            if (bs.getWeight() > v.getMaxWeight()) {
                JOptionPane.showMessageDialog(this, "Weight exceeds vehicle limit!");
                return;
            }

            // Assign 
            v.getShipments().add(s);
            s.setStatus("Assigned");

            JOptionPane.showMessageDialog(this, "Shipment assigned successfully!");
            dispose();
        });

        cancelBtn.addActionListener(e -> dispose());
    }
}

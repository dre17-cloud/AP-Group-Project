package client.gui;

import javax.swing.*;
import java.awt.*;
import main.Main;
import models.*;

public class CreateShipmentFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public CreateShipmentFrame() {
        setTitle("Create Shipment");
        setSize(400, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblRecipient = new JLabel("Recipient:");
        JLabel lblDest = new JLabel("Destination:");
        JLabel lblZone = new JLabel("Zone (1-4):");
        JLabel lblWeight = new JLabel("Weight (kg):");
        JLabel lblType = new JLabel("Type (Standard / Express / Fragile):");

        JTextField txtRecipient = new JTextField();
        JTextField txtDest = new JTextField();
        JTextField txtZone = new JTextField();
        JTextField txtWeight = new JTextField();
        JTextField txtType = new JTextField();

        JButton submitBtn = new JButton("Create");
        JButton cancelBtn = new JButton("Cancel");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblRecipient, gbc);
        gbc.gridx = 1; panel.add(txtRecipient, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblDest, gbc);
        gbc.gridx = 1; panel.add(txtDest, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(lblZone, gbc);
        gbc.gridx = 1; panel.add(txtZone, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(lblWeight, gbc);
        gbc.gridx = 1; panel.add(txtWeight, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(lblType, gbc);
        gbc.gridx = 1; panel.add(txtType, gbc);

        gbc.gridx = 0; gbc.gridy = 5; panel.add(submitBtn, gbc);
        gbc.gridx = 1; panel.add(cancelBtn, gbc);

        add(panel);
        
     
        submitBtn.addActionListener(e -> {
            try {
                String recipient = txtRecipient.getText().trim();
                String dest = txtDest.getText().trim();
                int zone = Integer.parseInt(txtZone.getText().trim());
                double weight = Double.parseDouble(txtWeight.getText().trim());
                String type = txtType.getText().trim();

                Shipment s = new BaseShipment("GUI Customer", recipient, dest, zone, weight, type);

                Main.shipments.add(s);
                Invoice inv = new Invoice(s);
                Main.invoices.add(inv);

                JOptionPane.showMessageDialog(this,
                        "Shipment Created!\nTracking #: " + s.getTrackingNumber());

                dispose();

            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error: " + ex.getMessage(),
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dispose());
    }
}
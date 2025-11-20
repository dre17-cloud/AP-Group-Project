package client.gui;

import server.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class UpdateStatusFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> shipmentSelect;
    private JComboBox<String> statusSelect;

    public UpdateStatusFrame() {
        setTitle("Update Delivery Status");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Load tracking numbers
        String[] trackingNumbers = loadTrackingNumbers();

        // Shipment selection
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Select Shipment:"), gbc);

        shipmentSelect = new JComboBox<>(trackingNumbers);
        gbc.gridx = 1;
        add(shipmentSelect, gbc);

        // Status selection
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
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(updateBtn, gbc);

        updateBtn.addActionListener(e -> updateStatus());
    }

    private String[] loadTrackingNumbers() {

        ArrayList<String> list = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT `Tracking Number` FROM baseshipment";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(String.valueOf(rs.getInt("Tracking Number")));
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading tracking numbers: " + e.getMessage());
        }

        return list.toArray(new String[0]);
    }

    private void updateStatus() {

        String tracking = shipmentSelect.getSelectedItem().toString();
        String newStatus = statusSelect.getSelectedItem().toString();

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "UPDATE baseshipment SET `Status`=? WHERE `Tracking Number`=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newStatus);
            ps.setInt(2, Integer.parseInt(tracking));

            int updated = ps.executeUpdate();
            ps.close();

            if (updated > 0) {
                JOptionPane.showMessageDialog(this,
                        "Status updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Shipment not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Database Error: " + e.getMessage(),
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

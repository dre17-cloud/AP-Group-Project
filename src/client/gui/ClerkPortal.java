package client.gui;

import javax.swing.*;
import java.awt.*;

public class ClerkPortal extends JFrame {

  
	private static final long serialVersionUID = 1L;

	public ClerkPortal() {
        setTitle("SmartShip - Clerk Portal");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel title = new JLabel("Clerk Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridwidth = 1;

        JButton assignBtn = new JButton("Assign Shipment to Vehicle");
        JButton updateStatusBtn = new JButton("Update Shipment Status");
        JButton managePaymentsBtn = new JButton("Manage Payments");
        JButton logoutBtn = new JButton("Logout");

        gbc.gridy = 1; gbc.gridx = 0; panel.add(assignBtn, gbc);
        gbc.gridy = 1; gbc.gridx = 1; panel.add(updateStatusBtn, gbc);
        gbc.gridy = 2; gbc.gridx = 0; panel.add(managePaymentsBtn, gbc);
        gbc.gridy = 2; gbc.gridx = 1; panel.add(logoutBtn, gbc);

        add(panel);

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        assignBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Assign Shipment feature (to be implemented)."));
        updateStatusBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Update Status feature (to be implemented)."));
        managePaymentsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Manage Payments feature (to be implemented)."));
    }
}

package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DriverPortal extends JFrame {

	private static final long serialVersionUID = 1L;

	public DriverPortal() {
        setTitle("SmartShip - Driver Portal");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel title = new JLabel("Driver Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridwidth = 1;

        JButton viewShipmentsBtn = new JButton("View Assigned Shipments");
        JButton updateStatusBtn = new JButton("Update Delivery Status");
        JButton logoutBtn = new JButton("Logout");

        gbc.gridy = 1; gbc.gridx = 0; panel.add(viewShipmentsBtn, gbc);
        gbc.gridy = 1; gbc.gridx = 1; panel.add(updateStatusBtn, gbc);
        gbc.gridy = 2; gbc.gridx = 0; gbc.gridwidth = 2; panel.add(logoutBtn, gbc);

        add(panel);

        viewShipmentsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewShipmentsFrame().setVisible(true);
            }
        });

        updateStatusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStatusFrame().setVisible(true);
            }
        });
        
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
     
        //viewShipmentsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "View Assigned Shipments feature (to be implemented)."));
        //updateStatusBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Update Delivery Status feature (to be implemented)."));
    }
}
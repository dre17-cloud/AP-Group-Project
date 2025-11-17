package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPortal extends JFrame {


	private static final long serialVersionUID = 1L;

        @SuppressWarnings("Convert2Lambda")
	public CustomerPortal() {
        setTitle("SmartShip - Customer Portal");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel title = new JLabel("Customer Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridwidth = 1;

        JButton createShipmentBtn = new JButton("Create Shipment");
        JButton trackBtn = new JButton("Track Package");
        JButton invoiceBtn = new JButton("View / Pay Invoices");
        JButton logoutBtn = new JButton("Logout");

        gbc.gridy = 1; gbc.gridx = 0; panel.add(createShipmentBtn, gbc);
        gbc.gridy = 1; gbc.gridx = 1; panel.add(trackBtn, gbc);
        gbc.gridy = 2; gbc.gridx = 0; panel.add(invoiceBtn, gbc);
        gbc.gridy = 2; gbc.gridx = 1; panel.add(logoutBtn, gbc);

        add(panel);

     createShipmentBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CreateShipment().setVisible(true);
        }
    });


    
    
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
    }
}

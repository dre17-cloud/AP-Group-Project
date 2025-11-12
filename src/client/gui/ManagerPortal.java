package client.gui;

import javax.swing.*;
import java.awt.*;

public class ManagerPortal extends JFrame {

  
	private static final long serialVersionUID = 1L;

	public ManagerPortal() {
        setTitle("SmartShip - Manager Portal");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel title = new JLabel("Manager Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridwidth = 1;

        JButton reportBtn = new JButton("Generate Reports");
        JButton fleetBtn = new JButton("Manage Fleet");
        JButton userBtn = new JButton("Manage Users");
        JButton logoutBtn = new JButton("Logout");

        gbc.gridy = 1; gbc.gridx = 0; panel.add(reportBtn, gbc);
        gbc.gridy = 1; gbc.gridx = 1; panel.add(fleetBtn, gbc);
        gbc.gridy = 2; gbc.gridx = 0; panel.add(userBtn, gbc);
        gbc.gridy = 2; gbc.gridx = 1; panel.add(logoutBtn, gbc);

        add(panel);

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        reportBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Generate Reports feature (to be implemented)."));
        fleetBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Manage Fleet feature (to be implemented)."));
        userBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Manage Users feature (to be implemented)."));
    }
}

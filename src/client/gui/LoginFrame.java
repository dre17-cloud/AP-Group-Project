package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * SmartShip Login GUI using GridBagLayout
 */
public class LoginFrame extends JFrame {


	private static final long serialVersionUID = 1L;
	private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JButton loginBtn, registerBtn;

    public LoginFrame() {
        setTitle("SmartShip - Login");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Root panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("SMARTSHIP LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Email
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        // Password
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // Role
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        roleBox = new JComboBox<>(new String[]{"Customer", "Clerk", "Driver", "Manager"});
        panel.add(roleBox, gbc);

        // Buttons
        gbc.gridy = 4;
        gbc.gridx = 0;
        loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 153, 76));
        loginBtn.setForeground(Color.WHITE);
        panel.add(loginBtn, gbc);

        gbc.gridx = 1;
        registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(204, 153, 0));
        registerBtn.setForeground(Color.WHITE);
        panel.add(registerBtn, gbc);

        add(panel);
        setupActions();
    }

    private void setupActions() {
        loginBtn.addActionListener(this::handleLogin);
        registerBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Registration window coming soon.")
        );
    }

    private void handleLogin(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String role = roleBox.getSelectedItem().toString();

        if (email.isBlank() || password.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            return;
        }

        // later: verify with database or socket client
        JOptionPane.showMessageDialog(this, "Login successful as " + role + "!");
        dispose();

        switch (role) {
            case "Customer" -> new CustomerPortal().setVisible(true);
            case "Clerk" -> new ClerkPortal().setVisible(true);
            case "Driver" -> new DriverPortal().setVisible(true);
            case "Manager" -> new ManagerPortal().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }

}

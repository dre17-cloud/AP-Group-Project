import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShipmentToVechicle extends JFrame {
    private JLabel vehicleId;
    private JComboBox VH001, VH002, VH003;

    public ShipmentToVechicle(){
        setSize(600, 400);
        setTitle("Assign Shipment To Vehicle");
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src/textures/box.png").getImage());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Assign Shipment To Vehicle");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,30,0);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel,gbc);

        Image truck = new ImageIcon("src/textures/cargo.gif").getImage();
        JLabel cargo = new JLabel(new ImageIcon(truck));
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(cargo, gbc);

        JLabel trackingNumberLabel = new JLabel("Tracking Number");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(trackingNumberLabel, gbc);

        JLabel trackingNumber = new JLabel("#");
        gbc.gridy = 2;
        gbc.gridx = 1;
        add(trackingNumber, gbc);

        JLabel vehicleIdLabel = new JLabel("Vehicles");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(vehicleIdLabel, gbc);

        JLabel vehicleId = new JLabel("#");
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(vehicleId, gbc);

        //Something must go here but I low-key high-key don't understand it
        //Call kehvoi for this - Kehvoi

        JLabel realtimeMessage = new JLabel("Shipment assigned");
        // fix that ^
        //ny fix it mean allow it to update with realtime messages, unless we don't wanna do that

        Image back = new ImageIcon("src/textures/return.png").getImage();
        JButton backButton = new JButton(new ImageIcon(back));
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 0, 10, 5);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //This goes back to clerk but I routed it to invoice, easy change
                new Invoice().setVisible(true);
               dispose();
            }
        });
        add(backButton,gbc);

        JButton submit = new JButton("Pay");
        gbc.gridy = 4;
        gbc.gridx = 1;
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ShipmentToVechicle.this, "Sending data");

            }
        });
        add(submit, gbc);

        setVisible(true);
    }
    public static void main(String[] args){
        new ShipmentToVechicle();
    }
}

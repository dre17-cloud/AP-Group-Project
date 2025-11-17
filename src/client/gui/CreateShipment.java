package client.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateShipment extends JFrame{
    private JTextField recipientValue, destinationValue, weightValue;
    private final JLabel recipientLabel,destinationLabel, weightLabel, typeLabel, zoneLabel;
    private JComboBox <String> typeValue, zoneValue;
    private final JButton submitShipment;

    private static final long serialVersionUID = 1L;


    @SuppressWarnings("Convert2Lambda")
    public CreateShipment(){
        setSize(600, 400);
        setTitle("Customer - CREATE SHIPMENT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Recipient
        recipientLabel = new JLabel("Recipient Name");
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(recipientLabel,gbc);

        recipientValue = new JTextField(30);
        gbc.gridy = 0;
        gbc.gridx = 1;
        add(recipientValue,gbc);

        //Destination
        destinationLabel = new JLabel("Destination");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(destinationLabel, gbc);

        destinationValue = new JTextField(50);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(destinationValue, gbc);

        zoneLabel = new JLabel("Zone");
        gbc.gridy = 1;
        gbc.gridx = 2;
        add(zoneLabel,gbc);

        zoneValue = new JComboBox<>(new String[]{"1", "2", "3", "4"});
        gbc.gridy = 1;
        gbc.gridx = 3;
        add(zoneValue, gbc);

        weightLabel = new JLabel("Weight (kg)");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(weightLabel, gbc);

        weightValue = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(weightValue, gbc);

        typeLabel = new JLabel("Type");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(typeLabel, gbc);

        typeValue = new JComboBox<>(new String[]{"Standard", "Express", "Fragile"});
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(typeValue, gbc);

        submitShipment = new JButton("Submit");
        gbc.gridy = 4;
        gbc.gridx = 1;
        add(submitShipment, gbc);

        submitShipment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                // Attach to database


                if (recipientValue.getText().isBlank() || destinationValue.getText().isBlank() || weightValue.getText().isBlank()){
                    JOptionPane.showMessageDialog(CreateShipment.this, "Complete all fields before continuing");

                }

                System.out.println(recipientValue.getText());
                System.out.println(destinationValue.getText());
                System.out.println(zoneValue.getSelectedItem());
                System.out.println(weightValue.getText());
                System.out.println(typeValue.getSelectedItem());
                System.out.println("Senting..");
                JOptionPane.showMessageDialog(CreateShipment.this, "Shipment created successfully!");
                dispose();

            }
        });
    }

}

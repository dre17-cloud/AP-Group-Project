package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TrackShipment extends JFrame {

    private JLabel titleLabel, trackingNumber, trackingNumberLabel, shipmentStatusLabel, shipmentStatus;
    private JButton back;

    public TrackShipment(){
        setSize(600, 400);
        setTitle("Track Shipment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        titleLabel = new JLabel("Track Shipment");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,30,0);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel,gbc);


        trackingNumberLabel = new JLabel("Tracking Number");
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(trackingNumberLabel, gbc);

        trackingNumber = new JLabel(":");
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(trackingNumber, gbc);

        shipmentStatusLabel = new JLabel("Shipment Status");
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(shipmentStatusLabel, gbc);

        shipmentStatus = new JLabel(":");
        gbc.gridy = 2;
        gbc.gridx = 1;
        add(shipmentStatus, gbc);

        back = new JButton("Back");
        gbc.gridy = 10;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 0, 0, 0);
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                // new CreateShipment().setVisible(true);
                dispose();
            }
        });
        add(back, gbc);

        setVisible(true);
    }



    public static void main(String[] args){
        System.out.println("Everything is working fine");
        new TrackShipment();
    }
}


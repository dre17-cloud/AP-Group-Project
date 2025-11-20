import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ViewShipments extends JFrame{
    private JLabel titleLabel, trackingNumberLabel, trackingNumber, senderLabel, sender, recipientLabel, recipient, destinationLabel, destination, zoneLabel, zone, weightLabel, weight, typeLabel, type, costLabel, cost, statusLabel, status;
    private JButton backButton, download;

    public ViewShipments(){
        setSize(600, 400);
        setTitle("View Shipments");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/textures/box.png").getImage());


        //Background image
        setContentPane(new JLabel(new ImageIcon("src/textures/paper.jpg")));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        titleLabel = new JLabel("Tracking Number");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,30,0);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel,gbc);

        //Spacing for components
        gbc.insets = new Insets(5, 5, 5, 5);

        trackingNumberLabel = new JLabel("Tracking Number");
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(trackingNumberLabel, gbc);
        trackingNumber = new JLabel("# ");
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(trackingNumber, gbc);

        senderLabel = new JLabel("Sender");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(senderLabel, gbc);
        sender = new JLabel();
        gbc.gridy = 2;
        gbc.gridx = 1;
        add(sender, gbc);

        recipientLabel = new JLabel("Recipient");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(recipientLabel, gbc);
        recipient = new JLabel();
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(recipient, gbc);

        destinationLabel = new JLabel("Destination");
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(destinationLabel, gbc);
        destination = new JLabel("");
        gbc.gridy = 4;
        gbc.gridx = 1;
        add(destination, gbc);

        zoneLabel = new JLabel("Zone");
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(zoneLabel, gbc);
        zone = new JLabel("Zone ");
        gbc.gridy = 5;
        gbc.gridx = 1;
        add(zone, gbc);

        weightLabel = new JLabel("Weight");
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(weightLabel, gbc);
        weight = new JLabel("kg");
        gbc.gridy = 6;
        gbc.gridx = 1;
        add(weight, gbc);

        typeLabel = new JLabel("Type");
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(typeLabel, gbc);
        type = new JLabel("");
        gbc.gridy = 7;
        gbc.gridx = 1;
        add(type, gbc);

        costLabel = new JLabel("Cost");
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(costLabel, gbc);
        cost = new JLabel("$");
        gbc.gridy = 8;
        gbc.gridx = 1;
        add(cost, gbc);

        statusLabel = new JLabel("Status");
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(statusLabel, gbc);
        status = new JLabel("");
        gbc.gridy = 9;
        gbc.gridx = 1;
        add(status, gbc);
        //Space for next items
        //gbc.insets = new Insets(0,0,30,0);

        Image back = new ImageIcon("src/textures/return.png").getImage();
        backButton = new JButton(new ImageIcon(back));
        gbc.gridy = 10;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 0, 0, 0);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new CreateShipment().setVisible(true);
                dispose();
            }
        });
        add(backButton,gbc);

        //get images
       Image prev = new ImageIcon("src/textures/prev.png").getImage();
       Image next = new ImageIcon("src/textures/next.png").getImage();


       JButton prevButton = new JButton(new ImageIcon(prev));
       gbc.gridy = 10;
       gbc.gridx = 1;
       prevButton.setContentAreaFilled(false);
       prevButton.setBorderPainted(false);
       prevButton.setFocusPainted(false);
       prevButton.setOpaque(false);
       prevButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //Goes to the previous set in database
           }
       });

       add(prevButton, gbc);

       JButton nextButton = new JButton(new ImageIcon(next));
       gbc.gridy = 10;
       gbc.gridx = 2;
       nextButton.setContentAreaFilled(false);
       nextButton.setBorderPainted(false);
       nextButton.setFocusPainted(false);
       nextButton.setOpaque(false);
       nextButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //Goes to the next set in the database
           }
       });
       add(nextButton, gbc);




        setVisible(true);
    }
    public static void main(String[] args){
        new ViewShipments();
    }


}

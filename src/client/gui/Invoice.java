import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Invoice extends JFrame{

    private JLabel titleLabel, invoiceLabel, shipmentLabel, amountLabel, amountPaidLabel, outstandingBalanceLabel, statusLabel, dateLabel, invoiceValue, shipmentValue, amount, amountPaid, outstandingBalance, status, date;

    public Invoice(){

        setSize(600, 400);
        setTitle("INVOICE ");
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src/textures/box.png").getImage());

        GridBagConstraints gbc = new GridBagConstraints();


        titleLabel = new JLabel("Invoice");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,30,0);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel,gbc);

        //Spacing for components
        gbc.insets = new Insets(5, 5, 5, 5);

        invoiceLabel = new JLabel("Invoice");
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(invoiceLabel,gbc);

        //This comes from the database add in the JLabel("#" + datafrom database)
        invoiceValue = new JLabel("#" );
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(invoiceValue, gbc);

        shipmentLabel = new JLabel("Shipment");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(shipmentLabel, gbc);

        shipmentValue = new JLabel("# " );
        gbc.gridy = 2;
        gbc.gridx = 1;
        add(shipmentValue, gbc);

        amountLabel = new JLabel("Amount");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(amountLabel, gbc);

        amount = new JLabel("$");
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(amount, gbc);

        amountPaidLabel = new JLabel("Amount Paid");
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(amountPaidLabel, gbc);

        amountPaid = new JLabel("$");
        gbc.gridy = 4;
        gbc.gridx = 1;
        add(amountPaid, gbc);

        outstandingBalanceLabel = new JLabel("Outstanding Balance");
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(outstandingBalanceLabel, gbc);

        //Gonna remove the $ incase of a negative balance
        outstandingBalance = new JLabel("$");
        gbc.gridy = 5;
        gbc.gridx = 1;
        add(outstandingBalance, gbc);

        statusLabel = new JLabel("Status");
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(statusLabel, gbc);

        // Add the status from the database in this;
        status = new JLabel(" ");
        gbc.gridy = 6;
        gbc.gridx = 1;
        add(status, gbc);

        dateLabel = new JLabel("Date");
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(dateLabel, gbc);

        date = new JLabel("");
        gbc.gridy = 7;
        gbc.gridx = 1;
        add(date,gbc);

        Image back = new ImageIcon("src/textures/return.png").getImage();
        JButton backButton = new JButton(new ImageIcon(back));
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

        JButton payButton = new JButton("$ Pay");
        gbc.gridy = 10;
        gbc.gridx = 1;
        add(payButton, gbc);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Payment().setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args){
        new Invoice();
    }

}

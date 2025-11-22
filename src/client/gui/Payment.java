package client.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment extends JFrame {
    public JLabel titleLabel,amountLabel, amountPaid, outstandingBalanceLabel, outstandingBalance;


    public Payment(){

        setSize(600, 400);
        setTitle("INVOICE ");
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src/textures/box.png").getImage());
        GridBagConstraints gbc = new GridBagConstraints();

        titleLabel = new JLabel("Payment");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,30,0);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel,gbc);

        //Spacing for components
        gbc.insets = new Insets(5, 5, 5, 5);

        outstandingBalanceLabel = new JLabel("Outstanding Amount");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(outstandingBalanceLabel, gbc);

        outstandingBalance = new JLabel("$");
        gbc.gridy = 2;
        gbc.gridx = 1;
        add(outstandingBalance, gbc);

        amountLabel = new JLabel("Amount Pay");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(amountLabel, gbc);

        amountPaid = new JLabel("$");
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(amountPaid, gbc);

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
                new Invoice().setVisible(true);
                dispose();
            }
        });
        add(backButton,gbc);

        JButton pay = new JButton("Pay");
        gbc.gridy = 4;
        gbc.gridx = 1;
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Payment.this, "Sending data");

            }
        });
        add(pay, gbc);







        setVisible(true);
    }

    public static void main(String[] args){
        System.out.println("Hello honey i'm home!");
        new Payment();
    }

}


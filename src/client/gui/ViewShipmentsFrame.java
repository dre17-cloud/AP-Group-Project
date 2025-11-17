package client.gui;

import client.data.DriverShipmentDatabase;
import models.BaseShipment;

import java.util.ArrayList;

import javax.swing.*;


public class ViewShipmentsFrame extends JFrame {

    public ViewShipmentsFrame() {
        setTitle("Assigned Shipments");
        setSize(650, 400);
        setLocationRelativeTo(null);

        ArrayList<BaseShipment> list = DriverShipmentDatabase.getAllShipments();

        String[] columns = {
                "Tracking #", "Sender", "Recipient",
                "Destination", "Weight", "Type", "Status"
        };

        String[][] data = new String[list.size()][7];

        for (int i = 0; i < list.size(); i++) {
            BaseShipment s = list.get(i);
            data[i][0] = s.getTrackingNumber();
            data[i][1] = s.getSenderName();
            data[i][2] = s.getRecipientName();
            data[i][3] = s.getDestination();
            data[i][4] = String.valueOf(s.getWeight());
            data[i][5] = s.getType();
            data[i][6] = s.getStatus();
        }

        JTable table = new JTable(data, columns);
        add(new JScrollPane(table));
    }
}


package client.gui;

import server.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ViewShipmentsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public ViewShipmentsFrame() {
        setTitle("Assigned Shipments");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {
                "Tracking #",
                "Sender",
                "Recipient",
                "Destination",
                "Zone",
                "Weight",
                "Type",
                "Status",
                "Cost"
        };

        // Load data from MySQL
        String[][] tableData = loadShipmentsFromDatabase();

        JTable table = new JTable(tableData, columns);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private String[][] loadShipmentsFromDatabase() {

        ArrayList<String[]> rows = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = """
                SELECT 
                    `Tracking Number`,
                    `Sender Name`,
                    `Recipient Name`,
                    `Destination`,
                    `Zone`,
                    `Weight`,
                    `Type`,
                    `Status`,
                    `Cost`
                FROM baseshipment
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                rows.add(new String[]{
                        String.valueOf(rs.getInt("Tracking Number")),
                        rs.getString("Sender Name"),
                        rs.getString("Recipient Name"),
                        rs.getString("Destination"),
                        String.valueOf(rs.getInt("Zone")),
                        String.valueOf(rs.getInt("Weight")),
                        rs.getString("Type"),
                        rs.getString("Status"),
                        String.valueOf(rs.getDouble("Cost"))
                });
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading shipments: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }

  
        return rows.toArray(new String[0][0]);
    }
}

//Joel Atkinson December 14, 2025 CSD420 Advanced Java Programming Assignment 10.2
/* The purpose of this assignment is to build a GUI that connects to a SQL database called 'fans' and allows you to update
the database through this GUI by changing ID numbers, first names, last names, and favorite teams.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FanManager extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/databasedb";
    private static final String DB_USER = "student1";
    private static final String DB_PASSWORD = "pass";

    private JTextField txtId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtFavoriteTeam;

    private JButton btnDisplay;
    private JButton btnUpdate;

    private Connection connection;

    public FanManager() {
        setTitle("Fan Information Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(20);
        add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        txtFirstName = new JTextField(20);
        add(txtFirstName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        txtLastName = new JTextField(20);
        add(txtLastName, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Favorite Team:"), gbc);
        gbc.gridx = 1;
        txtFavoriteTeam = new JTextField(20);
        add(txtFavoriteTeam, gbc);

        JPanel buttonPanel = new JPanel();
        btnDisplay = new JButton("Display Record");
        btnUpdate = new JButton("Update Record");
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnUpdate);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        connectToDatabase();

        btnDisplay.addActionListener(e -> displayRecord());
        btnUpdate.addActionListener(e -> updateRecord());
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to connect to database:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void displayRecord() {
        String idText = txtId.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter an ID Number");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Must Be a Number");
            return;
        }

        String query = "SELECT firstname, lastname, favoriteteam FROM fans WHERE ID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    txtFirstName.setText(rs.getString("firstname"));
                    txtLastName.setText(rs.getString("lastname"));
                    txtFavoriteTeam.setText(rs.getString("favoriteteam"));
                } else {
                    JOptionPane.showMessageDialog(this, "No Fan Found with ID " + id);
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtFavoriteTeam.setText("");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error Retrieving Record:\n" + e.getMessage());
        }
    }

    private void updateRecord() {
        String idText = txtId.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter an ID Number");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be a number.");
            return;
        }

        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String favoriteTeam = txtFavoriteTeam.getText().trim();

        String query = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, favoriteTeam);
            pstmt.setInt(4, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "No record found with ID " + id + " to update");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error Updating Record:\n" + e.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        super.finalize();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FanManager().setVisible(true);
        });
    }
}

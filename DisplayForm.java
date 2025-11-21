import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DisplayForm extends JFrame {
    JTextField idField, nameField, addressField, contactField;
    JRadioButton maleRadio, femaleRadio;
    ButtonGroup genderGroup;
    JButton registerButton, exitButton;
    JTable table;
    DefaultTableModel model;

    public DisplayForm() {
        setTitle("Registration Form");
        setSize(800, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // vertical stacking

        // --- Form Panel ---
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        idField = new JTextField();
        nameField = new JTextField();
        addressField = new JTextField();
        contactField = new JTextField();
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        formPanel.add(new JLabel("ID")); formPanel.add(idField);
        formPanel.add(new JLabel("Name")); formPanel.add(nameField);
        formPanel.add(new JLabel("Gender"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadio); genderPanel.add(femaleRadio);
        formPanel.add(genderPanel);
        formPanel.add(new JLabel("Address")); formPanel.add(addressField);
        formPanel.add(new JLabel("Contact")); formPanel.add(contactField);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout());
        registerButton = new JButton("Register");
        exitButton = new JButton("Exit");
        buttonPanel.add(registerButton);
        buttonPanel.add(exitButton);

        // --- Table Panel ---
        model = new DefaultTableModel(new String[]{"ID", "Name", "Gender", "Address", "Contact"}, 0);
        table = new JTable(model);
        JScrollPane tableScroll = new JScrollPane(table);

        // --- Add panels in order ---
        add(formPanel);
        add(buttonPanel);
        add(tableScroll);

        // --- Button Actions ---
        registerButton.addActionListener(e -> registerUser());
        exitButton.addActionListener(e -> System.exit(0));

        loadTableData();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void registerUser() {
        String id = idField.getText();
        String name = nameField.getText();
        String gender = maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "";
        String address = addressField.getText();
        String contact = contactField.getText();

        if (id.isEmpty() || name.isEmpty() || gender.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields.");
            return;
        }

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/registration_db", "root", "7725Le@v")) {
            
            String sql = "INSERT INTO users (id, name, gender, address, mobile) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            pst.setString(2, name);
            pst.setString(3, gender);
            pst.setString(4, address);
            pst.setString(5, contact);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "User registered successfully!");
            loadTableData();
            clearFields();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadTableData() {
        model.setRowCount(0); // clear table
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/registration_db", "root", "your_password_here")) {
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, gender, address, mobile FROM users");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getString("address"),
                    rs.getString("mobile")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        genderGroup.clearSelection();
        addressField.setText("");
        contactField.setText("");
    }

    public static void main(String[] args) {
        new DisplayForm();
    }
}
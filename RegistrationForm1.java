/*Author:Levis kipruto chumba
REG :BSE-01-0036/2025
Desccription: program to create registration FORm*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm1 extends JFrame {
    JTextField nameField, mobileField;
    JRadioButton male, female;
    JComboBox<String> day, month, year;
    JTextArea addressArea;
    JCheckBox terms;
    JButton submit, reset;
    ButtonGroup genderGroup;

    public RegistrationForm1() {
        setTitle("Registration Form");
        setSize(400, 500);
        setLayout(new GridLayout(9, 2));

        // Initialize components
        nameField = new JTextField();
        mobileField = new JTextField();
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        day = new JComboBox<>();
        month = new JComboBox<>();
        year = new JComboBox<>();
        for (int i = 1; i <= 31; i++) day.addItem(String.valueOf(i));
        for (int i = 1; i <= 12; i++) month.addItem(String.valueOf(i));
        for (int i = 1980; i <= 2025; i++) year.addItem(String.valueOf(i));

        addressArea = new JTextArea();
        terms = new JCheckBox("Accept Terms And Conditions");
        submit = new JButton("Submit");
        reset = new JButton("Reset");

        // Add components to frame
        add(new JLabel("Name")); add(nameField);
        add(new JLabel("Mobile")); add(mobileField);
        add(new JLabel("Gender")); add(male); add(female);
        add(new JLabel("DOB")); add(day); add(month); add(year);
        add(new JLabel("Address")); add(new JScrollPane(addressArea));
        add(terms); add(submit); add(reset);

        // Button actions
        submit.addActionListener(e -> saveData());
        reset.addActionListener(e -> clearForm());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void saveData() {
        if (!terms.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please accept the terms.");
            return;
        }

        String name = nameField.getText();
        String mobile = mobileField.getText();
        String gender = male.isSelected() ? "Male" : "Female";
        String dob = year.getSelectedItem() + "-" + month.getSelectedItem() + "-" + day.getSelectedItem();
        String address = addressArea.getText();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_db", "root", "7725Le@v")) {
            String sql = "INSERT INTO users (name, mobile, gender, dob, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, mobile);
            pst.setString(3, gender);
            pst.setString(4, dob);
            pst.setString(5, address);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration successful!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void clearForm() {
        nameField.setText("");
        mobileField.setText("");
        genderGroup.clearSelection();
        day.setSelectedIndex(0);
        month.setSelectedIndex(0);
        year.setSelectedIndex(0);
        addressArea.setText("");
        terms.setSelected(false);
    }

    public static void main(String[] args) {
        new RegistrationForm1();
    }
}